package org.wavemoney.intern4.payment.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wavemoney.intern4.payment.api.dto.UserRequest;
import org.wavemoney.intern4.payment.api.dto.UserResponse;
import org.wavemoney.intern4.payment.api.service.UserService;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest user) {
        UserResponse userResponse = userService.createUser(user);
        return ResponseEntity.ok(userResponse);
    }
}
