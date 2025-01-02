package com.libraryhub.library_backend.service.impl;

import java.time.LocalDate;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.libraryhub.library_backend.dto.UserDTO;
import com.libraryhub.library_backend.model.User;
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

    @Override
    public UserDTO verify(UserDTO userRequest) {
        User user = userRepository.findByUsername(userRequest.getUsername());
        if (user == null) {
            throw new IllegalStateException("User not found.");
        }
        if (!encoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw new IllegalStateException("Invalid credentials.");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.getUsername(),
                        userRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            jwtService.generateToken(userRequest.getUsername());
            log.info("User {} verified successfully.", user.getUsername());
            return UserDTO.builder()
                    .id(user.getId())
                    .token(jwtService.generateToken(userRequest.getUsername()))
                    .build();
        } else {
            log.warn("Authentication failed for user {}", userRequest.getUsername());
            return UserDTO.builder()
                    .message("Authentication failed.")
                    .build();
        }

    }

    @Override
    public UserDTO registerUser(UserDTO userRequest) {

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new IllegalStateException("Username already exists.");
        }

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new IllegalStateException("Email already registered.");
        }

        User user = User.builder()
                .username(userRequest.getUsername())
                .name(userRequest.getName())
                .email(userRequest.getEmail())
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
