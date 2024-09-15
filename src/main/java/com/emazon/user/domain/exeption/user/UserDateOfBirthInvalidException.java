package com.emazon.user.domain.exeption.user;

public class UserDateOfBirthInvalidException extends RuntimeException {
    public UserDateOfBirthInvalidException(String message) {
        super(message);
    }
}