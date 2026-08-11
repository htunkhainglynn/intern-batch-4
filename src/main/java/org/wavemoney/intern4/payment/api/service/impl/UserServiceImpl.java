package org.wavemoney.intern4.payment.api.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Service;
import org.wavemoney.intern4.payment.api.dto.UserRequest;
import org.wavemoney.intern4.payment.api.dto.UserResponse;
import org.wavemoney.intern4.payment.api.entity.User;
import org.wavemoney.intern4.payment.api.repo.UserRepository;
import org.wavemoney.intern4.payment.api.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        // DTO to Entity Mapping
        User user = mapDtoToEntity(userRequest);

        // to DB
        User savedUser = userRepo.createUser(user);

        // Entity to DTO Mapping
        UserResponse userResponse = mapEntitytoDto(savedUser);

        return userResponse;
    }

    private User mapDtoToEntity(UserRequest userRequest) {
        User user = new User();
        user.setId("123");
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setAge(userRequest.getAge());

        return user;
    }

    private UserResponse mapEntitytoDto(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getUsername());
        userResponse.setAge(user.getAge());

        return userResponse;
    }
}

