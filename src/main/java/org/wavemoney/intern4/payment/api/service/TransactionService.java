package org.wavemoney.intern4.payment.api.service;

import jakarta.validation.Valid;
import org.wavemoney.intern4.payment.api.dto.request.CashinRequest;
import org.wavemoney.intern4.payment.api.dto.request.CashoutRequest;
import org.wavemoney.intern4.payment.api.dto.request.TransferRequest;
import org.wavemoney.intern4.payment.api.dto.response.TransactionResponse;

import java.util.List;

public interface TransactionService {

    TransactionResponse transfer(TransferRequest transferRequest);
    TransactionResponse cashin(CashinRequest cashinRequest);
    TransactionResponse cashout(CashoutRequest cashoutRequest);
    List<TransactionResponse> getAllTransactions();

}
