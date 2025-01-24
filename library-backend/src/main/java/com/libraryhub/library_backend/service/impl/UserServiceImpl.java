package com.libraryhub.library_backend.service.impl;

import java.time.LocalDate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libraryhub.library_backend.dto.AuthDTO;
import com.libraryhub.library_backend.dto.UserDTO;
import com.libraryhub.library_backend.exception.DuplicateResourceException;
import com.libraryhub.library_backend.model.User;
import com.libraryhub.library_backend.model.UserPrinciple;
import com.libraryhub.library_backend.repository.UserRepository;
import com.libraryhub.library_backend.service.JWTService;
import com.libraryhub.library_backend.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    @Override
    public AuthDTO login(UserDTO userRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword()));

        if (authentication.isAuthenticated()) {

            User user = userRepository.findByEmail(userRequest.getEmail());
            if (user == null) {
                throw new RuntimeException("User not found.");
            }

            UserPrinciple userPrinciple = new UserPrinciple(user);

            String accessToken = jwtService.generateToken(userPrinciple);
            String refreshToken = jwtService.generateRefreshToken(userPrinciple);

            log.info("User {} verified successfully.", userRequest.getEmail());
            return AuthDTO.builder()
                    .userId(user.getId())
                    .role(user.getRole())
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .expirationTime("24Hrs")
                    .message("Successfully Logged In")
                    .build();
        } else {
            log.warn("Authentication failed for user {}", userRequest.getEmail());
            throw new RuntimeException("Invalid credentials.");
        }
    }

    @Transactional
    @Override
    public UserDTO registerUser(UserDTO userRequest) {

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new DuplicateResourceException("Username already exists.");
        }

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new DuplicateResourceException("Email already registered.");
        }

        User user = User.builder()
                .username(userRequest.getUsername())
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .role(userRequest.getRole())
                .password(encoder.encode(userRequest.getPassword()))
                .registeredDate(LocalDate.now())
                .isActive(true)
                .build();
        userRepository.save(user);
        log.info("User {} registered successfully.", user.getUsername());

        return UserDTO.builder()
                .id(user.getId())
                .message("User registered successfully.")
                .build();

    }

}
