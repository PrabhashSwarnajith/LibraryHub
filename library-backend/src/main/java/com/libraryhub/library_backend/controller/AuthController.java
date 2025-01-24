package com.libraryhub.library_backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryhub.library_backend.dto.UserDTO;
import com.libraryhub.library_backend.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping(value = "/singup")
    public UserDTO registerUser(@RequestBody UserDTO userRequest) {
        return userService.registerUser(userRequest);
    }

    @PostMapping(value = "/login")
    public UserDTO login(@RequestBody UserDTO userRequest) {
        return userService.verify(userRequest);
    }

}
