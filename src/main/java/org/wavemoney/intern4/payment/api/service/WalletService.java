package org.wavemoney.intern4.payment.api.service;

import org.wavemoney.intern4.payment.api.dto.request.WalletRequest;
import org.wavemoney.intern4.payment.api.dto.response.WalletResponse;

import java.util.List;

public interface WalletService {
    WalletResponse createWallet(WalletRequest walletRequest);
    WalletResponse getWalletByPhone(String phone);
    List<WalletResponse> getAllWallets();
    void deleteWalletByPhone(String phone);
}
