package org.wavemoney.intern4.payment.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String userId;

    private String name;
    private String phone;
    private String nrc;
    private String pin;

    // KYC information
    private String address;
    private String dateOfBirth;
    private String gender;
    private String nationality;
    private String occupation;

    // KYC status
    private String kycStatus = "NOT SUBMITTED";

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

    @Builder.Default
    private String level = "1";
}
