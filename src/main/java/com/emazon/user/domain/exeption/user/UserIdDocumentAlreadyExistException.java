package com.emazon.user.domain.exeption.user;

public class UserIdDocumentAlreadyExistException extends RuntimeException {
    public UserIdDocumentAlreadyExistException(String message) {
        super(message);
    }
}