package org.wavemoney.intern4.payment.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "transactions")
public class Transaction {

    @Id
    private String transactionId;
    private String sender;
    private String receiver;
    private Double amount;
    private String status;
    private String transactionType;
    private LocalDateTime createdAt;
}
