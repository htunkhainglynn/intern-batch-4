package org.wavemoney.intern4.payment.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    String id;
    String username;
    String password;
    int age;
}
