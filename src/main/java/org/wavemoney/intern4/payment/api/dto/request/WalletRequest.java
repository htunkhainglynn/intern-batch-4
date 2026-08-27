package org.wavemoney.intern4.payment.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wavemoney.intern4.payment.api.enums.Currency;
import org.wavemoney.intern4.payment.api.enums.WalletStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class WalletRequest {
    private String phone;
    Currency currency;
}
