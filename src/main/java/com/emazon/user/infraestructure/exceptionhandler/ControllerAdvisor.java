package com.emazon.user.infraestructure.exceptionhandler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.emazon.user.domain.exeption.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;


@ControllerAdvice
public class ControllerAdvisor {
    private static final String MESSAGE = "Message";


    private ResponseEntity<Map<String, String>> buildResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .body(Collections.singletonMap(MESSAGE, message));
    }

    @ExceptionHandler({
            UserDateOfBirthRequiredException.class,
            UserDateOfBirthInvalidException.class,
            UserEmailInvalidException.class,
            UserEmailRequiredException.class,
            UserIdDocumentRequiredException.class,
            UserIdDocumentInvalidException.class,
            UserLastNameRequiredException.class,
            UserLastNameInvalidException.class,
            UserNameRequiredException.class,
            UserNameInvalidException.class,
            UserPasswordRequiredException.class,
            UserPasswordInvalidException.class,
            UserPhoneNumberRequiredException.class,
            UserPhoneNumberInvalidException.class,
    })
    public ResponseEntity<Map<String, String>> handleBadRequestExceptions(RuntimeException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler({
            UserEmailAlreadyExistException.class,
            UserIdDocumentAlreadyExistException.class
    })
    public ResponseEntity<Map<String, String>> handleConflictExceptions(RuntimeException ex) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler({
            UserBadCredentialsException.class,
            JWTVerificationException.class
    })
    public ResponseEntity<Map<String, String>> handleAuthenticationExceptions(RuntimeException ex) {
        return buildResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }


}
