package org.wavemoney.intern4.payment.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    String name;
    String phone;
    String nrc;
    String address;
    String dateOfBirth;
    String gender;
    String nationality;
    String occupation;
    String level;
    String walletStatus;
    String kycStatus;
}
