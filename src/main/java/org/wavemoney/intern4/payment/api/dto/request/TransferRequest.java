package org.wavemoney.intern4.payment.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TransferRequest {
    String sender;
    String receiver;
    Double amount;
}
