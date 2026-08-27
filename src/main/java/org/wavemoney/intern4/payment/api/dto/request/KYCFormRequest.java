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

public class KYCFormRequest {
    @NotBlank(message = "name is required")
    String name;

    @NotBlank(message = "phone is required")
    @Pattern(regexp = "^[0-9+\\-\\s]{6,20}$", message = "phone format is invalid")
    String phone;

    @NotBlank(message = "NRC is required")
    String nrc;

    @NotBlank(message = "Address is required")
    String address;

    @NotBlank(message = "Date of Birth is required")
    String dateOfBirth;

    @NotBlank(message = "Gender is required")
    String gender;

    @NotBlank(message = "Nationality is required")
    String nationality;

    @NotBlank(message = "Occupation is required")
    String occupation;

}
