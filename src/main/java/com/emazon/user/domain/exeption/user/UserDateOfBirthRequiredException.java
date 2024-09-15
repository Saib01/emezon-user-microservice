package com.emazon.user.domain.exeption.user;

public class UserDateOfBirthRequiredException extends RuntimeException {
    public UserDateOfBirthRequiredException(String message) {
        super(message);
    }
}