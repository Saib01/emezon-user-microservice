package com.emazon.user.domain.exeption.user;

public class UserEmailInvalidException extends RuntimeException {
    public UserEmailInvalidException(String message) {
        super(message);
    }
}