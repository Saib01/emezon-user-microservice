package com.emazon.user.application.handler.implement;

import com.emazon.user.application.dtos.AuthLoginRequest;
import com.emazon.user.application.dtos.AuthResponse;
import com.emazon.user.application.dtos.JwtPayloadResponse;
import com.emazon.user.application.handler.IAuthenticationHandler;
import com.emazon.user.domain.api.IAuthenticationServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.emazon.user.application.dtos.JwtPayloadResponse.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationHandler implements IAuthenticationHandler {
    private final IAuthenticationServicePort authenticationServicePort;

    @Override
    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
        return new AuthResponse(authenticationServicePort.loginUser(authLoginRequest.getUsername(), authLoginRequest.getPassword()));
    }

    @Override
    public JwtPayloadResponse getJwtPayload() {
        Map<String, String> jwtPayloadMap = authenticationServicePort.getJwtPayload();
        return new JwtPayloadResponse(jwtPayloadMap.get(ID_STRING), jwtPayloadMap.get(EMAIL_STRING), jwtPayloadMap.get(ROLE_STRING));
    }
}
