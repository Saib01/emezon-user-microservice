package com.emazon.user.domain.spi;


import java.util.Map;

public interface IAuthenticationPersistencePort {
    String generateToken(String username, String role);

    String getRole(String inputPassword, String storedHashedPassword);

    Map<String, String> getJwtPayload();
}
