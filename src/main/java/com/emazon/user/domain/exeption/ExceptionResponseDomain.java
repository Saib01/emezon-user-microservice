package com.emazon.user.domain.exeption;

import static com.emazon.user.domain.utils.DomainConstants.*;
import static com.emazon.user.domain.utils.ErrorTemplates.*;
import static java.lang.String.format;

public enum ExceptionResponseDomain {

    USER_NAME_REQUIRED(format(REQUIRED, MODEL_USER, PROPERTY_NAME)),
    USER_PHONE_NUMBER_REQUIRED(format(REQUIRED, MODEL_USER, PROPERTY_PHONE_NUMBER)),
    USER_LAST_NAME_REQUIRED(format(REQUIRED, MODEL_USER, PROPERTY_LAST_NAME)),
    USER_DATE_OF_BIRTH_REQUIRED(format(REQUIRED, MODEL_USER, PROPERTY_DATE_OF_BIRTH)),
    USER_PASSWORD_REQUIRED(format(REQUIRED, MODEL_USER, PROPERTY_PASSWORD)),
    USER_EMAIL_REQUIRED(format(REQUIRED, MODEL_USER, PROPERTY_EMAIL)),
    USER_ID_DOCUMENT_REQUIRED(format(REQUIRED, MODEL_USER, PROPERTY_ID_DOCUMENT)),
    USER_NAME_INVALID(format(INVALID, MODEL_USER, PROPERTY_NAME)),
    USER_PHONE_NUMBER_INVALID(format(INVALID, MODEL_USER, PROPERTY_PHONE_NUMBER)),
    USER_LAST_NAME_INVALID(format(INVALID, MODEL_USER, PROPERTY_LAST_NAME)),
    USER_DATE_OF_BIRTH_INVALID(format(INVALID, MODEL_USER, PROPERTY_DATE_OF_BIRTH)),
    USER_PASSWORD_INVALID(format(INVALID, MODEL_USER, PROPERTY_PASSWORD)),
    USER_EMAIL_INVALID(format(INVALID, MODEL_USER, PROPERTY_EMAIL)),
    USER_ID_DOCUMENT_INVALID(format(INVALID, MODEL_USER, PROPERTY_ID_DOCUMENT)),
    USER_EMAIL_ALREADY_EXISTS(format(ALREADY_EXISTS, MODEL_USER, PROPERTY_EMAIL)),
    USER_ID_DOCUMENT_ALREADY_EXISTS(format(ALREADY_EXISTS, MODEL_USER, PROPERTY_ID_DOCUMENT)),
    USER_PASSWORD_INCORRECT(AUTHENTICATION_ERROR),
    USER_NOT_FOUND("User not found.");
    private final String message;

    ExceptionResponseDomain(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}