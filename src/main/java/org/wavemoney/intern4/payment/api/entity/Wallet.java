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
@Document(collection="wallets")
@NoArgsConstructor
@Builder
public class Wallet {

    @Id
    private String walletId;
    private String userId;
    private String phone;
    private Double balance;
    private String status;
    private String currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}
