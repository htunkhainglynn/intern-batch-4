package org.wavemoney.intern4.payment.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wavemoney.intern4.payment.api.dto.request.PinUpdateRequest;
import org.wavemoney.intern4.payment.api.dto.request.UpdateUserRequest;
import org.wavemoney.intern4.payment.api.dto.request.UserRequest;
import org.wavemoney.intern4.payment.api.dto.request.WalletRequest;
import org.wavemoney.intern4.payment.api.dto.response.LoginResponse;
import org.wavemoney.intern4.payment.api.dto.response.UserResponse;
import org.wavemoney.intern4.payment.api.dto.response.WalletResponse;
import org.wavemoney.intern4.payment.api.entity.User;
import org.wavemoney.intern4.payment.api.repo.UserRepository;
import org.wavemoney.intern4.payment.api.config.security.JwtService;
import org.wavemoney.intern4.payment.api.config.security.TokenService;
import org.wavemoney.intern4.payment.api.service.UserService;
import org.wavemoney.intern4.payment.api.service.WalletService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final WalletService walletService;
    private final JwtService jwtService;
    private final TokenService tokenService;

    @Override
    public UserResponse create(UserRequest userRequest) {
        Optional<User> optionalUser = userRepo.findByPhone(userRequest.getPhone());

        if (optionalUser.isPresent()) {
            throw new RuntimeException("user already exists");
        }

        // DTO → Entity
        User user = mapDtoToEntity(userRequest);

        // Save User to DB
        User savedUser = userRepo.save(user);

        // Create Wallet for User
        WalletRequest walletRequest = new WalletRequest();
        walletRequest.setPhone(savedUser.getPhone());
        WalletResponse walletResponse = walletService.createWallet(walletRequest);

        // Entity → DTO
        return mapEntityToDto(savedUser, walletResponse);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepo.findAll();

        // Convert each User Entity → UserResponse DTO
        List<UserResponse> userResponses = users.stream().map(this::mapEntityToDto).toList();

        return userResponses;
    }

    @Override
    public UserResponse getUserByPhone(String phone) {
        Optional<User> optionalUser = userRepo.findByPhone(phone);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("user not found");
        }

        // Entity → DTO
        return mapEntityToDto(optionalUser.get());
    }

    @Override
    public UserResponse updateUser(UpdateUserRequest updateUserRequest) {
        Optional<User> optionalUser = userRepo.findByPhone(updateUserRequest.getPhone());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("user not found");
        }

        User user = optionalUser.get();

        // DTO → Entity
        User userToUpdate = mapDtoToEntity(updateUserRequest, user);

        // Save to DB
        User updatedUser = userRepo.save(userToUpdate);

        // Entity → DTO
        UserResponse userResponse = mapEntityToDto(updatedUser);

        return userResponse;
    }

    @Override
    public LoginResponse login(String phone, String pin) {
        Optional<User> optionalUser = userRepo.findByPhone(phone);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("user not found");
        }

        User user = optionalUser.get();

        if (!user.getPin().equals(pin)) {
            throw new RuntimeException("invalid credentials");
        }

        // Entity → DTO
        UserResponse userResponse = mapEntityToDto(user);

        // Generate token
        String token = jwtService.issue(user.getPhone(), "ACTIVE");
        tokenService.markTokenAsActive(user.getPhone(), jwtService.expirationMs());

        return LoginResponse.builder()
                .user(userResponse)
                .accessToken(token)
                .tokenType("Bearer")
                .expiresInMs(jwtService.expirationMs())
                .build();
    }

    @Override
    public void changePin(PinUpdateRequest pinUpdateRequest) {
        Optional<User> optionalUser = userRepo.findByPhone(pinUpdateRequest.getPhone());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("user not found");
        }

        User user = optionalUser.get();
        String oldPin = pinUpdateRequest.getOldPin();
        String newPin = pinUpdateRequest.getNewPin();

        if (!user.getPin().equals(oldPin)) {
            throw new RuntimeException("Old PIN does not match.");
        }

        if (oldPin.equals(newPin)) {
            throw new RuntimeException("NEW pin must be different from OLD pin");
        }

        user.setPin(newPin);
        userRepo.save(user);
    }

    @Override
    public void verifyPin(String phone, String pin) {
        Optional<User> optionalUser = userRepo.findByPhone(phone);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("user not found");
        }

        User user = optionalUser.get();

        if (!user.getPin().equals(pin)) {
            throw new RuntimeException("invalid credentials");
        }
    }

    @Override
    public void delete(String phone) {
        Optional<User> optionalUser = userRepo.findByPhone(phone);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("user not found");
        }

        userRepo.deleteByPhone(phone);
        walletService.deleteWalletByPhone(phone);
    }

    @Override
    public void logout(String phone) {
        Optional<User> optionalUser = userRepo.findByPhone(phone);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("user not found");
        }

        tokenService.markTokenAsLoggedOut(phone);
    }

    private User mapDtoToEntity(UserRequest userRequest) {
        User user = User.builder()
                .userId(UUID.randomUUID().toString())
                .name(userRequest.getName())
                .phone(userRequest.getPhone())
                .pin(userRequest.getPin())
                .nrc(userRequest.getNrc())
                .createdAt(LocalDateTime.now())
                .build();

        return user;
    }

    private User mapDtoToEntity(UpdateUserRequest updateUserRequest, User oldUserData) {
        User user = User.builder()
                .userId(oldUserData.getUserId())
                .name(updateUserRequest.getName())
                .phone(updateUserRequest.getPhone())
                .pin(oldUserData.getPin())
                .nrc(updateUserRequest.getNrc())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .updatedBy("User")
                .build();

        return user;
    }

    private UserResponse mapEntityToDto(User user) {
        WalletResponse walletResponse = walletService.getWalletByPhone(user.getPhone());
        return mapEntityToDto(user, walletResponse);
    }

    private UserResponse mapEntityToDto(User user, WalletResponse walletResponse) {
        UserResponse userResponse = UserResponse.builder()
                .name(user.getName())
                .level(user.getLevel())
                .phone(user.getPhone())
                .walletStatus(walletResponse.getStatus())
                .build();

        return userResponse;
    }
}