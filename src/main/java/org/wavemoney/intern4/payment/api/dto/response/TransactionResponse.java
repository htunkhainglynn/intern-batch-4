package org.wavemoney.intern4.payment.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TransactionResponse {
    String transactionId;
    String sender;
    String receiver;
    Double amount;
    String status;
    String transactionType;
    LocalDateTime createdAt;
}
