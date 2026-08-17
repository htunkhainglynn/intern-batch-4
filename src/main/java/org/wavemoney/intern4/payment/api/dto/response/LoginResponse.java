package org.wavemoney.intern4.payment.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LoginResponse {
    UserResponse user;
    String accessToken;
    String tokenType;
    long expiresInMs;
}
