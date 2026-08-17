package org.wavemoney.intern4.payment.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wavemoney.intern4.payment.api.dto.request.LoginRequest;
import org.wavemoney.intern4.payment.api.dto.request.PinUpdateRequest;
import org.wavemoney.intern4.payment.api.dto.request.UpdateUserRequest;
import org.wavemoney.intern4.payment.api.dto.request.UserRequest;
import org.wavemoney.intern4.payment.api.dto.response.LoginResponse;
import org.wavemoney.intern4.payment.api.dto.response.UserResponse;
import org.wavemoney.intern4.payment.api.service.UserService;

import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest user) {
        UserResponse userResponse = userService.create(user);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> userResponses = userService.getAllUsers();
        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/{phone}")
    public ResponseEntity<UserResponse> getUserByPhone(@PathVariable String phone) {
        UserResponse userResponse = userService.getUserByPhone(phone);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        UserResponse userResponse = userService.updateUser(updateUserRequest);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = userService.login(loginRequest.getPhone(), loginRequest.getPin());
        return ResponseEntity.ok(loginResponse);
    }

    @DeleteMapping("/{phone}")
    public ResponseEntity<String> delete(@PathVariable String phone){
        userService.delete(phone);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PutMapping("/change-pin")
    public ResponseEntity<String> changePin(@Valid @RequestBody PinUpdateRequest pinUpdateRequest){
        userService.changePin(pinUpdateRequest);
        return ResponseEntity.ok("PIN updated successfully");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody Map<String, String> logoutRequest) {
        String phone = logoutRequest.get("phone");
        userService.logout(phone);
        return ResponseEntity.ok("Logged out successfully");
    }
}
