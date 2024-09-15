package com.emazon.user.domain.exeption.user;

public class UserNameRequiredException extends RuntimeException {
    public UserNameRequiredException(String message) {
        super(message);
    }
}