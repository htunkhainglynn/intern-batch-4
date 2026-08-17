package org.wavemoney.intern4.payment.api.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wavemoney.intern4.payment.api.dto.request.WalletRequest;
import org.wavemoney.intern4.payment.api.dto.response.WalletResponse;
import org.wavemoney.intern4.payment.api.entity.Wallet;
import org.wavemoney.intern4.payment.api.repo.WalletRepository;
import org.wavemoney.intern4.payment.api.service.WalletService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepo;

    @Override
    public WalletResponse createWallet(WalletRequest walletRequest){

        Optional<Wallet> optionalWallet = walletRepo.findByPhone(walletRequest.getPhone());

        if (optionalWallet.isPresent()){
            throw new RuntimeException("wallet already exists");
        }

        Wallet wallet = mapDtoToEntity(walletRequest);
        Wallet savedWallet = walletRepo.save(wallet);
        WalletResponse walletResponse = mapEntityToDto(savedWallet);

        return walletResponse;
    }

    @Override
    public WalletResponse getWalletByPhone(String phone){

        Optional<Wallet> optionalWallet = walletRepo.findByPhone(phone);

        if (optionalWallet.isEmpty()) {
            throw new RuntimeException("wallet not found");
        }

        WalletResponse walletResponse = mapEntityToDto(optionalWallet.get());

        return walletResponse;
    }

    @Override
    public List<WalletResponse> getAllWallets(){

        List<Wallet> wallets = walletRepo.findAll();

        //Entity to Dto
        List<WalletResponse> walletResponses = wallets.stream().map(this::mapEntityToDto).toList();

        return walletResponses;
    }

    @Override
    public void deleteWalletByPhone(String phone) {

        Optional<Wallet> optionalWallet = walletRepo.findByPhone(phone);

        if (optionalWallet.isEmpty()) {
            throw new RuntimeException("wallet not found");
        }

        walletRepo.deleteByPhone(phone);
    }


    private Wallet mapDtoToEntity(WalletRequest walletRequest) {
        Wallet wallet = Wallet.builder()
                .walletId(UUID.randomUUID().toString())
                .phone(walletRequest.getPhone())
                .balance(0.0)
                .status("ACTIVE")
                .build();
        return wallet;
    }

    private WalletResponse mapEntityToDto(Wallet wallet){
        WalletResponse walletResponse = WalletResponse.builder()
                .walletId(wallet.getWalletId())
                .phone(wallet.getPhone())
                .balance(wallet.getBalance())
                .status(wallet.getStatus())
                .build();
        return walletResponse;
    }
}
