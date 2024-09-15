package com.emazon.user.domain.exeption.user;

public class UserPasswordRequiredException extends RuntimeException {
    public UserPasswordRequiredException(String message) {
        super(message);
    }
}