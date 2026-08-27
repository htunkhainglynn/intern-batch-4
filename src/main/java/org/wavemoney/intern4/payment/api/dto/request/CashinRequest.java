package org.wavemoney.intern4.payment.api.dto.request;

import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

public class CashinRequest {
    String receiver;

    @Positive
    Double amount;
}
