package com.emazon.user.domain.exeption.user;

public class UserLastNameInvalidException extends RuntimeException {
    public UserLastNameInvalidException(String message) {
        super(message);
    }
}