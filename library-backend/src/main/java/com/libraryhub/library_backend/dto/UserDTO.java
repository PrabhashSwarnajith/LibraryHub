package com.libraryhub.library_backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.libraryhub.library_backend.model.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private String id;
    private String email;
    private String password;
    private String name;
    private Role role;
    private String registeredDate;
    private Boolean isActive;
    private String message;
    private String statusCode;
}
