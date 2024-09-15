package com.emazon.user.domain.exeption.user;

public class UserEmailAlreadyExistException extends RuntimeException {
    public UserEmailAlreadyExistException(String message) {
        super(message);
    }
}