package com.emazon.user.domain.exeption.user;

public class UserPhoneNumberRequiredException extends RuntimeException {
    public UserPhoneNumberRequiredException(String message) {
        super(message);
    }
}