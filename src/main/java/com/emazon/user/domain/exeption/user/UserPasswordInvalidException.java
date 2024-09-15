package com.emazon.user.domain.exeption.user;

public class UserPasswordInvalidException extends RuntimeException {
    public UserPasswordInvalidException(String message) {
        super(message);
    }
}