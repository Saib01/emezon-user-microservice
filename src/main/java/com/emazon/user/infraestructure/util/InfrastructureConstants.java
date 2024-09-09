package com.emazon.user.infraestructure.util;

public class InfrastructureConstants {
    public static final String BEARER = "Bearer";
    public static final String AUTHORITIES="authorities";
    public static final String JWT_KEY_GENERATOR="${security.jwt.key.private}";
    public static final String JWT_USER_GENERATOR="${security.jwt.user.generator}";

    private InfrastructureConstants() {
    }
}
