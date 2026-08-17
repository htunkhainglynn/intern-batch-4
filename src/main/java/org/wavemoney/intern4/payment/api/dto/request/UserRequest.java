package org.wavemoney.intern4.payment.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserRequest {
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "phone is required")
    @Pattern(regexp = "^[0-9+\\-\\s]{6,20}$", message = "phone format is invalid")
    private String phone;

    @NotBlank(message = "NRC is required")
    private String nrc;

    @NotBlank(message = "pin is required")
    @Pattern(regexp = "^(?!.*([0-9])\\1{3}).*$", message = "pin must be 4 digits")
    private String pin;
}
