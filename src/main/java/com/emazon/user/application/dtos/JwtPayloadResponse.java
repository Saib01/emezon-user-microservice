package com.emazon.user.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtPayloadResponse {
    public static final String EMAIL_STRING = "email";
    public static final String ROLE_STRING = "role";
    public static final String ID_STRING = "id";
    private String id;
    private String email;
    private String role;
}
