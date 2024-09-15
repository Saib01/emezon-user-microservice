package com.emazon.user.domain.exeption.user;

public class UserIdDocumentInvalidException extends RuntimeException {
    public UserIdDocumentInvalidException(String message) {
        super(message);
    }
}