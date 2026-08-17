package org.wavemoney.intern4.payment.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wavemoney.intern4.payment.api.dto.request.CashinRequest;
import org.wavemoney.intern4.payment.api.dto.request.CashoutRequest;
import org.wavemoney.intern4.payment.api.dto.request.LoginRequest;
import org.wavemoney.intern4.payment.api.dto.request.TransferRequest;
import org.wavemoney.intern4.payment.api.dto.response.TransactionResponse;
import org.wavemoney.intern4.payment.api.service.TransactionService;
import org.wavemoney.intern4.payment.api.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final UserService userService;

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> transfer(@RequestBody TransferRequest transferRequest){
        TransactionResponse transactionResponse = transactionService.transfer(transferRequest);
        return ResponseEntity.ok(transactionResponse);
    }

    @PostMapping("/cashin")
    public ResponseEntity<TransactionResponse> cashin(@Valid @RequestBody CashinRequest cashinRequest){
        TransactionResponse transactionResponse = transactionService.cashin(cashinRequest);
        return ResponseEntity.ok(transactionResponse);
    }

    @PostMapping("/cashout")
    public ResponseEntity<TransactionResponse> cashout(@Valid @RequestBody CashoutRequest cashoutRequest){
        TransactionResponse transactionResponse = transactionService.cashout(cashoutRequest);
        return ResponseEntity.ok(transactionResponse);
    }

    @PostMapping("/verify-pin")
    public ResponseEntity<String> verifyPin(@RequestBody LoginRequest loginRequest){
        userService.verifyPin(loginRequest.getPhone(), loginRequest.getPin());
        return ResponseEntity.ok("Pin verified");
    }
}
