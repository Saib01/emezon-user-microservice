package com.emazon.user.domain.api;

import java.util.Map;

public interface IAuthenticationServicePort {
    String loginUser(String username, String password);

    Map<String, String> getJwtPayload();
}
