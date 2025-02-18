package com.emazon.user.domain.utils;

public class ErrorTemplates {

    public static final String ALREADY_EXISTS = "There is already a %s with that %s";
    public static final String REQUIRED = "The %s %s cannot be null or empty.";
    public static final String INVALID = "The %s %s is invalid.";
    public static final String AUTHENTICATION_ERROR = "Invalid credentials,please check your username or password.";

    private ErrorTemplates() {
    }
}
