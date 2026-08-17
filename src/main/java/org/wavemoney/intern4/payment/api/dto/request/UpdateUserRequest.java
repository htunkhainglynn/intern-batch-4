package org.wavemoney.intern4.payment.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UpdateUserRequest {

    @NotBlank(message = "name is required")
    String name;

    @NotBlank(message = "nrc is required")
    String nrc;

    @NotBlank(message = "level is required")
    String level;

    @NotBlank(message = "phone is required")
    String phone;
}
