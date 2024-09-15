package com.emazon.user.domain.exeption.user;

public class UserBadCredentialsException extends RuntimeException {
    public UserBadCredentialsException(String message) {
        super(message);
    }
}