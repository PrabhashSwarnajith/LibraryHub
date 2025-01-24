package com.libraryhub.library_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ErrorDTO {
    private int statusCode;
    private String message;
}
