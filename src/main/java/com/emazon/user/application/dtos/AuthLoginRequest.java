package com.emazon.user.application.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthLoginRequest {
    private String username;
    private String password;

}
