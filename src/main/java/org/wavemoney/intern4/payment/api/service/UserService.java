package org.wavemoney.intern4.payment.api.service;

import org.wavemoney.intern4.payment.api.dto.UserRequest;
import org.wavemoney.intern4.payment.api.dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
}
