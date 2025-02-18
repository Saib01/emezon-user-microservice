package com.emazon.user.domain.utils;

public class DomainConstants {
    public static final String MODEL_USER = "User";
    public static final String PROPERTY_NAME = "Name";
    public static final String PROPERTY_LAST_NAME = "LastName";
    public static final String PROPERTY_ID_DOCUMENT = "IdDocument";
    public static final String PROPERTY_PHONE_NUMBER = "PhoneNumber";
    public static final String PROPERTY_DATE_OF_BIRTH = "DateOfBirth";
    public static final String PROPERTY_PASSWORD = "Password";
    public static final String PROPERTY_EMAIL = "Email";
    public static final Long MINIMUM_AGE_REQUIRED = 18L;
    public static final String ROLE_PREFIX = "ROLE_";
    public static final String AUX_ROLE_DESCRIPTION = "This is an auxiliary user";
    public static final String CLIENT_ROLE_DESCRIPTION = "This is a client user";
    public static final String KEY_TEMPLATE = "User%s%s";

    private DomainConstants() {
    }
}
