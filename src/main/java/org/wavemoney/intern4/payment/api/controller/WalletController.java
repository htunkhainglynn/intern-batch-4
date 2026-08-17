package org.wavemoney.intern4.payment.api.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wavemoney.intern4.payment.api.dto.response.WalletResponse;
import org.wavemoney.intern4.payment.api.service.WalletService;

import java.util.List;

@RestController
@RequestMapping ("/api/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping("/{phone}")
    public ResponseEntity<WalletResponse> getWalletByPhone(@PathVariable String phone) {
        WalletResponse walletResponse = walletService.getWalletByPhone(phone);
        return ResponseEntity.ok(walletResponse);
    }

    @GetMapping
    public ResponseEntity<List<WalletResponse>> getAllWallets(){
        List<WalletResponse> walletResponses = walletService.getAllWallets();
        return ResponseEntity.ok(walletResponses);
    }
}
