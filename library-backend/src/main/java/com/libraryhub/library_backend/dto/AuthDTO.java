package com.libraryhub.library_backend.dto;

import com.libraryhub.library_backend.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthDTO {
    private String userId;
    private Role role;
    private String accessToken;
    private String refreshToken;
    private String expirationTime;
    private String message;
}
