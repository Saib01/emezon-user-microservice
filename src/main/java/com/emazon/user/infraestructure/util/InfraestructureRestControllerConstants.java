package com.emazon.user.infraestructure.util;

public class InfraestructureRestControllerConstants {
    public static final String RESPONSE_CODE_SUCCESS = "200";
    public static final String RESPONSE_CODE_UNAUTHORIZED = "401";
    public static final String RESPONSE_DESCRIPTION_LOGIN_SUCCESSFUL = "Login successful";
    public static final String RESPONSE_DESCRIPTION_UNAUTHORIZED = "Unauthorized";
    public static final String RESPONSE_CODE_CREATED = "201";
    public static final String RESPONSE_CODE_CONFLICT = "409";
    public static final String RESPONSE_DESCRIPTION_USER_CREATED = "User created";
    public static final String RESPONSE_DESCRIPTION_USER_ALREADY_EXISTS = "User already exists";
    public static final String ADD_NEW_AUX_BODEGA = "Add a new aux bodega";
    public static final String ADD_NEW_CLIENT_BODEGA = "Add a new client";
    public static final String USER_LOGIN = "User login";
    public static final String API_BASE = "/api";
    public static final String USERS = "/users";
    public static final String AUX = "/aux";
    public static final String CLIENT = "/client";
    public static final String AUTH = "/auth";

    public static final String API_SIGNUP_AUX_PATH = String.format("%s%s%s", API_BASE, USERS, AUX);
    public static final String API_SIGNUP_CLIENT_PATH = String.format("%s%s%s", API_BASE, USERS, CLIENT);
    public static final String API_AUTH_PATH = String.format("%s%s", API_BASE, AUTH);

    private InfraestructureRestControllerConstants() {

    }
}
