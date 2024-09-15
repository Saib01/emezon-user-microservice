package com.emazon.user.utils;

import com.emazon.user.domain.utils.RoleEnum;

import java.time.LocalDate;

public class TestConstants {

    public static final Long VALID_ID = 1L;
    public static final String VALID_USER_NAME = "Johan";
    public static final String VALID_USER_LAST_NAME = "Santiago";
    public static final String VALID_USER_EMAIL = "mail@mail.com";
    public static final RoleEnum VALID_USER_ROLE = RoleEnum.ADMIN;
    public static final String VALID_USER_FOR_TOKEN = "ROLE_AUX_BODEGA";
    public static final String VALID_JWT_TOKEN = "mockedJwtToken";
    public static final String VALID_USER_ROLE_DESCRIPTION = "Dios del Sistema";
    public static final String VALID_USER_PHONE_NUMBER = "+573116322584";
    public static final LocalDate VALID_USER_DATE_OF_BIRTH = LocalDate.parse("1990-08-11");
    public static final String VALID_USER_ID_DOCUMENT = "123123123123";
    public static final String VALID_USER_PASSWORD = "icelaCreyoDem@siado3123";
    public static final String NULL_PROPERTY = null;
    public static final String EMPTY_PROPERTY = "";
    public static final String INVALID_USER_NAME = "Joh1an";
    public static final String INVALID_USER_LAST_NAME = "Sant1iago";
    public static final String INVALID_USER_EMAIL = "mailmail.com";
    public static final String INVALID_USER_PHONE_NUMBER = "+571233116322584";
    public static final LocalDate INVALID_USER_DATE_OF_BIRTH = LocalDate.parse("2008-08-11");
    public static final String INVALID_USER_ID_DOCUMENT = "12312a3123123";
    public static final String INVALID_USER_PASSWORD = "passwordicelaCreyoDem@siado3123";
    public static final String EMPTY_METHOD = "notImplemented()";

    private TestConstants() {
    }
}
