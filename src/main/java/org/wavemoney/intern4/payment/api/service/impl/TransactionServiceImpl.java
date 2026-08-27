package org.wavemoney.intern4.payment.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.wavemoney.intern4.payment.api.enums.TransactionStatus;
import org.wavemoney.intern4.payment.api.enums.TransactionType;
import org.wavemoney.intern4.payment.api.dto.request.CashinRequest;
import org.wavemoney.intern4.payment.api.dto.request.CashoutRequest;
import org.wavemoney.intern4.payment.api.dto.request.TransferRequest;
import org.wavemoney.intern4.payment.api.dto.response.TransactionResponse;
import org.wavemoney.intern4.payment.api.entity.Transaction;
import org.wavemoney.intern4.payment.api.entity.User;
import org.wavemoney.intern4.payment.api.entity.Wallet;
import org.wavemoney.intern4.payment.api.repo.TransactionRepository;
import org.wavemoney.intern4.payment.api.repo.UserRepository;
import org.wavemoney.intern4.payment.api.repo.WalletRepository;
import org.wavemoney.intern4.payment.api.service.TransactionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final UserRepository userRepo;
    private final WalletRepository walletRepo;
    private final TransactionRepository trxnRepo;

    @Value("${transaction-amount.max}")
    private double maxAmount;

    @Value("${transaction-amount.min}")
    private double minAmount;

    @Value("${wallet-limit}")
    private double walletLimit;

    @Override
    public TransactionResponse transfer(TransferRequest transferRequest) {

        if (transferRequest.getSender().equals(transferRequest.getReceiver())) {
            throw new RuntimeException("sender and receiver cannot be the same");
        }

        // Get and validate sender user
        User senderUser = getUser(transferRequest.getSender());
        validateUserLevel(senderUser);

        // Get and validate sender wallet
        Wallet senderWallet = getWallet(transferRequest.getSender());
        validateWalletStatus(senderWallet);

        // Get and validate receiver wallet
        Wallet receiverWallet = getWallet(transferRequest.getReceiver());
        validateWalletStatus(receiverWallet);

        Double amount = transferRequest.getAmount();
        validateAmount(amount);
        validateBalance(senderWallet, amount);
        validateWalletLimit(receiverWallet, amount);

        // Update balances
        senderWallet.setBalance(senderWallet.getBalance() - amount);
        receiverWallet.setBalance(receiverWallet.getBalance() + amount);

        walletRepo.save(senderWallet);
        walletRepo.save(receiverWallet);

        return saveTransaction(
                senderWallet.getPhone(),
                receiverWallet.getPhone(),
                amount,
                TransactionType.TRANSFER
        );
    }

    @Override
    public TransactionResponse cashin(CashinRequest cashinRequest) {

        // Get and validate receiver user
        User receiverUser = getUser(cashinRequest.getReceiver());
        validateUserLevel(receiverUser);

        // Get and validate receiver wallet
        Wallet wallet = getWallet(cashinRequest.getReceiver());
        validateWalletStatus(wallet);

        Double amount = cashinRequest.getAmount();
        validateAmount(amount);
        validateWalletLimit(wallet, amount);

        // Update balance
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepo.save(wallet);

        return saveTransaction(
                "SYSTEM",
                wallet.getPhone(),
                amount,
                TransactionType.CASHIN
        );
    }

    @Override
    public TransactionResponse cashout(CashoutRequest cashoutRequest) {

        // Get and validate sender user
        User senderUser = getUser(cashoutRequest.getSender());
        validateUserLevel(senderUser);

        // Get and validate sender wallet
        Wallet wallet = getWallet(cashoutRequest.getSender());
        validateWalletStatus(wallet);

        Double amount = cashoutRequest.getAmount();
        validateAmount(amount);
        validateBalance(wallet, amount);

        // Update balance
        wallet.setBalance(wallet.getBalance() - amount);
        walletRepo.save(wallet);

        return saveTransaction(
                wallet.getPhone(),
                "SYSTEM",
                amount,
                TransactionType.CASHOUT
        );
    }

    @Override
    public List<TransactionResponse> getAllTransactions() {
        return trxnRepo.findAll().stream().map(this::mapEntityToDto).toList();
    }

    private User getUser(String phone) {
        Optional<User> optionalUser = userRepo.findByPhone(phone);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("user account not found");
        }

        return optionalUser.get();
    }

    private Wallet getWallet(String phone) {
        Optional<Wallet> optionalWallet = walletRepo.findByPhone(phone);

        if (optionalWallet.isEmpty()) {
            throw new RuntimeException("wallet not found");
        }

        return optionalWallet.get();
    }

    private void validateUserLevel(User user) {
        if (!"2".equals(user.getLevel())) {
            throw new RuntimeException(
                    "user is not allowed to perform this transaction"
            );
        }
    }

    private void validateWalletStatus(Wallet wallet) {
        if (!"ACTIVE".equals(wallet.getStatus())) {
            throw new RuntimeException("wallet is not active");
        }
    }

    private void validateAmount(Double amount) {
        if (amount == null || amount < minAmount || amount > maxAmount) {
            throw new RuntimeException(
                    "transaction amount is outside the allowed limit"
            );
        }
    }

    private void validateBalance(Wallet wallet, Double amount) {
        if (wallet.getBalance() < amount) {
            throw new RuntimeException("insufficient balance");
        }
    }

    private void validateWalletLimit(Wallet wallet, Double amount) {
        if (wallet.getBalance() + amount > walletLimit) {
            throw new RuntimeException("wallet limit exceeded");
        }
    }

    private TransactionResponse saveTransaction(String sender, String receiver, Double amount, TransactionType transactionType) {

        Transaction transaction = Transaction.builder()
                .transactionId(UUID.randomUUID().toString())
                .sender(sender)
                .receiver(receiver)
                .amount(amount)
                .status(TransactionStatus.SUCCESS.name())
                .transactionType(transactionType.name())
                .createdAt(LocalDateTime.now())
                .build();

        Transaction savedTransaction = trxnRepo.save(transaction);

        return mapEntityToDto(savedTransaction);
    }

    private TransactionResponse mapEntityToDto(Transaction transaction) {
        return TransactionResponse.builder()
                .transactionId(transaction.getTransactionId())
                .sender(transaction.getSender())
                .receiver(transaction.getReceiver())
                .amount(transaction.getAmount())
                .status(transaction.getStatus())
                .transactionType(transaction.getTransactionType())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}