package com.emazon.user.domain.exeption.user;

public class UserEmailRequiredException extends RuntimeException {
    public UserEmailRequiredException(String message) {
        super(message);
    }
}