package com.emazon.user.domain.exeption.user;

public class UserNameInvalidException extends RuntimeException {
    public UserNameInvalidException(String message) {
        super(message);
    }
}