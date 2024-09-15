package com.emazon.user.domain.exeption.user;

public class UserLastNameRequiredException extends RuntimeException {
    public UserLastNameRequiredException(String message) {
        super(message);
    }
}