package com.libraryhub.library_backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryhub.library_backend.dto.UserDTO;
import com.libraryhub.library_backend.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/singup")
    public void registerUser(@RequestBody UserDTO userRequest) {
        userService.registerUser(userRequest);
    }

    @PostMapping(value = "/login")
    public void login(@RequestBody UserDTO userRequest) {
        userService.verify(userRequest);
    }

}
