package com.libraryhub.library_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String username;
    private String email;
    private String name;
    private String role;
    private String registeredDate;
    private Boolean isActive;
}
