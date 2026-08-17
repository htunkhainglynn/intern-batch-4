package org.wavemoney.intern4.payment.api.dto.request;

import jakarta.validation.constraints.NegativeOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@NegativeOrZero
@Builder

public class CashinRequest {
    String receiver;
    Double amount;
}
