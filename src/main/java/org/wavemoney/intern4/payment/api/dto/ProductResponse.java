package org.wavemoney.intern4.payment.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductResponse {

    String id;
    String name;
    double price;
}
