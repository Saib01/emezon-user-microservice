package com.emazon.user.domain.exeption.user;

public class UserPhoneNumberInvalidException extends RuntimeException {
    public UserPhoneNumberInvalidException(String message) {
        super(message);
    }
}