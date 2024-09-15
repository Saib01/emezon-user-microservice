package com.emazon.user.domain.exeption.user;

public class UserIdDocumentRequiredException extends RuntimeException {
    public UserIdDocumentRequiredException(String message) {
        super(message);
    }
}