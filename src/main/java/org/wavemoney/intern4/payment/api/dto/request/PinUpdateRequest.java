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

public class PinUpdateRequest {
    @NotBlank(message = "phone is required")
    private String phone;

    @NotBlank(message = "old pin is required")
    private String oldPin;

    @NotBlank(message = "new pin is required")
    @Pattern(regexp = "^[0-9]{4}$", message = "pin must be 4 digits")
    @Pattern(regexp = "^(?!.*([0-9])\\1{3}).*$", message = "pin cannot have repeating digits")
    private String newPin;
}
