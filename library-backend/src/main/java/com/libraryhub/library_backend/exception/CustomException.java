package com.libraryhub.library_backend.exception;

public class CustomException {

    public class DuplicateResourceException extends RuntimeException {
        public DuplicateResourceException(String message) {
            super(message);
        }
    }

    public class ValidationException extends RuntimeException {
        public ValidationException(String message) {
            super(message);
        }
    }
}
