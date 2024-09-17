package com.emazon.user.application.handler;

import com.emazon.user.application.dtos.AuthLoginRequest;
import com.emazon.user.application.dtos.AuthResponse;
import com.emazon.user.application.dtos.JwtPayloadResponse;

public interface IAuthenticationHandler {
    AuthResponse loginUser(AuthLoginRequest userRequest);

    JwtPayloadResponse getJwtPayload();
}
