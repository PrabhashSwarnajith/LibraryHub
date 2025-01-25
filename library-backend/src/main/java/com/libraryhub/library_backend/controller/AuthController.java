package com.libraryhub.library_backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryhub.library_backend.dto.AuthDTO;
import com.libraryhub.library_backend.dto.UserDTO;
import com.libraryhub.library_backend.service.AuthService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService userService;

    @PostMapping(value = "/register")
    public UserDTO registerUser(@RequestBody UserDTO userRequest) {
        return userService.registerUser(userRequest);
    }

    @PostMapping(value = "/login")
    public AuthDTO login(@RequestBody UserDTO userRequest) {
        return userService.login(userRequest);
    }

}
