package com.emazon.user.infraestructure.configuration.jwt;


import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class JwtPayload {
    private String id;
    private String email;
    private String role;

    public JwtPayload setId(String id) {
        this.id = id;
        return this;
    }

    public JwtPayload setEmail(String email) {
        this.email = email;
        return this;
    }

    public JwtPayload setRole(String role) {
        this.role = role;
        return this;
    }
}
