package org.wavemoney.intern4.payment.api.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Service;
import org.wavemoney.intern4.payment.api.dto.UserRequest;
import org.wavemoney.intern4.payment.api.dto.UserResponse;
import org.wavemoney.intern4.payment.api.entity.User;
import org.wavemoney.intern4.payment.api.repo.UserRepository;
import org.wavemoney.intern4.payment.api.service.UserService;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        // DTO to Entity Mapping
        User user = mapDtoToEntity(userRequest);

        // to DB
        User savedUser = userRepo.save(user);

        // Entity to DTO Mapping
        UserResponse userResponse = mapEntitytoDto(savedUser);

        return userResponse;
    }

    private User mapDtoToEntity(UserRequest userRequest) {
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .name(userRequest.getName())
                .phone(userRequest.getPhone())
                .pin(userRequest.getPin())
                .nrc(userRequest.getNrc())
                .build();
        return user;
    }

    private UserResponse mapEntitytoDto(User user) {
        UserResponse userResponse = UserResponse.builder()
                .name(user.getName())
                .level(user.getLevel())
                .phone(user.getPhone())
                .build();

        return userResponse;
    }
}

