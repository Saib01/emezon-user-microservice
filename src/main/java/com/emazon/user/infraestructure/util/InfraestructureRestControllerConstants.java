package com.emazon.user.infraestructure.util;

public class InfraestructureRestControllerConstants {
    public static final String RESPONSE_CODE_SUCCESS = "200";
    public static final String RESPONSE_CODE_UNAUTHORIZED = "401";
    public static final String RESPONSE_DESCRIPTION_LOGIN_SUCCESSFUL = "Login successful";
    public static final String RESPONSE_DESCRIPTION_UNAUTHORIZED = "Unauthorized";
    public static final String RESPONSE_CODE_CREATED = "201";
    public static final String RESPONSE_CODE_CONFLICT = "409";
    public static final String RESPONSE_CODE_BAD_REQUEST = "400";

    public static final String RESPONSE_DESCRIPTION_USER_CREATED = "User created";
    public static final String RESPONSE_DESCRIPTION_USER_ALREADY_EXISTS = "User already exists";
    public static final String RESPONSE_DESCRIPTION_USER_ME = "Successfully retrieved JWT payload";
    public static final String RESPONSE_DESCRIPTION_USER_EMAIL_SUCCESSFUL = "Email availability checked successfully";
    public static final String RESPONSE_DESCRIPTION_USER_EMAIL_INVALID_FORMAT = "Invalid email format";
    public static final String RESPONSE_DESCRIPTION_USER_ID_DOCUMENT = "ID_STRING document availability checked successfully";
    public static final String RESPONSE_DESCRIPTION_USER_ID_DOCUMENT_INVALID_FORMAT = "Invalid ID_STRING document format";

    public static final String ADD_NEW_AUX_BODEGA = "Add a new aux bodega";
    public static final String ADD_NEW_CLIENT_BODEGA = "Add a new client";
    public static final String USER_LOGIN = "User login";
    public static final String USER_ME = "Retrieve the JWT payload for the logged-in user";
    public static final String VALIDATE_USER_EMAIL = "Validate if an email is available";
    public static final String VALIDATE_USER_ID_DOCUMENT = "Validate if an id document is available";

    public static final String API_BASE = "/api";
    public static final String API_FORMAT = "%s%s%s";
    public static final String USERS = "/users";
    public static final String AUX = "/aux";
    public static final String CLIENT = "/client";
    public static final String AUTH = "/auth";
    public static final String VALIDATE_EMAIL = "/validate-email";
    public static final String VALIDATE_ID_DOCUMENT = "/validate-id-document";

    public static final String API_SIGNUP_AUX_PATH = String.format(API_FORMAT, API_BASE, USERS, AUX);
    public static final String API_SIGNUP_CLIENT_PATH = String.format(API_FORMAT, API_BASE, USERS, CLIENT);
    public static final String API_AUTH_PATH = String.format("%s%s", API_BASE, AUTH);
    public static final String API_VALIDATE_EMAIL = String.format(API_FORMAT, API_BASE, USERS, VALIDATE_EMAIL);
    public static final String API_VALIDATE_ID_DOCUMENT = String.format(API_FORMAT, API_BASE, USERS, VALIDATE_ID_DOCUMENT);

    private InfraestructureRestControllerConstants() {

    }
}
