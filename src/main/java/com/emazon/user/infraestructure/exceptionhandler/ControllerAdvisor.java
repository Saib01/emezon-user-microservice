package com.emazon.user.infraestructure.exceptionhandler;

import com.emazon.user.domain.exeption.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.emazon.user.infraestructure.exceptionhandler.ExceptionResponse.*;

@ControllerAdvice
public class ControllerAdvisor {
    private static final String MESSAGE = "Message";
    private static final Map<Class<? extends Exception>, String> exceptionMap = new HashMap<>();

    static {
        exceptionMap.put(UserNameRequiredException.class,USER_NAME_REQUIRED.getMessage());
        exceptionMap.put(UserLastNameRequiredException.class,USER_LAST_NAME_REQUIRED.getMessage());
        exceptionMap.put(UserIdDocumentRequiredException.class, USER_ID_DOCUMENT_REQUIRED.getMessage());
        exceptionMap.put(UserPhoneNumberRequiredException.class, USER_PHONE_NUMBER_REQUIRED.getMessage());
        exceptionMap.put(UserDateOfBirthRequiredException.class, USER_DATE_OF_BIRTH_REQUIRED.getMessage());
        exceptionMap.put(UserPasswordRequiredException.class, USER_PASSWORD_REQUIRED.getMessage());
        exceptionMap.put(UserEmailRequiredException.class, USER_EMAIL_REQUIRED.getMessage());


        exceptionMap.put(UserNameInvalidException.class, USER_NAME_INVALID.getMessage());
        exceptionMap.put(UserLastNameInvalidException.class, USER_LAST_NAME_INVALID.getMessage());
        exceptionMap.put(UserIdDocumentInvalidException.class, USER_ID_DOCUMENT_INVALID.getMessage());
        exceptionMap.put(UserPhoneNumberInvalidException.class, USER_PHONE_NUMBER_INVALID.getMessage());
        exceptionMap.put(UserDateOfBirthInvalidException.class, USER_DATE_OF_BIRTH_INVALID.getMessage());
        exceptionMap.put(UserPasswordInvalidException.class, USER_PASSWORD_INVALID.getMessage());
        exceptionMap.put(UserEmailInvalidException.class, USER_EMAIL_INVALID.getMessage());

        exceptionMap.put(UserEmailAlreadyExistException.class, USER_EMAIL_ALREADY_EXISTS.getMessage());
        exceptionMap.put(UserIdDocumentAlreadyExistException.class, USER_ID_DOCUMENT_ALREADY_EXISTS.getMessage());
    }
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
            UserPhoneNumberInvalidException.class
    })
    public ResponseEntity<Map<String, String>> handleBadRequestExceptions(RuntimeException ex) {
        String message = exceptionMap.get(ex.getClass());
        return buildResponse(HttpStatus.BAD_REQUEST, message);
    }
    @ExceptionHandler({
            UserEmailAlreadyExistException.class,
            UserIdDocumentAlreadyExistException.class
    })
    public ResponseEntity<Map<String, String>> handleConflictExceptions(RuntimeException ex) {
        String message = exceptionMap.get(ex.getClass());
        return buildResponse(HttpStatus.CONFLICT, message);
    }
}
