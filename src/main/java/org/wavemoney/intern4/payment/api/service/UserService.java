package org.wavemoney.intern4.payment.api.service;

import org.wavemoney.intern4.payment.api.dto.request.KYCFormRequest;
import org.wavemoney.intern4.payment.api.dto.request.PinUpdateRequest;
import org.wavemoney.intern4.payment.api.dto.request.UpdateUserRequest;
import org.wavemoney.intern4.payment.api.dto.request.UserRequest;
import org.wavemoney.intern4.payment.api.dto.response.LoginResponse;
import org.wavemoney.intern4.payment.api.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse create(UserRequest userRequest);
    UserResponse getUserByPhone(String phone);
    UserResponse updateUser(String phone, KYCFormRequest kycFormRequest);
    LoginResponse login(String phone, String pin);
    List<UserResponse> getAllUsers();
    void changePin(PinUpdateRequest pinUpdateRequest);
    void verifyPin(String phone, String pin);
    void delete(String phone);
    void logout(String phone);
    UserResponse approveKyc(String phone);
    UserResponse rejectKyc(String phone);
}
