package com.emazon.user.application.handler.implement;

import com.emazon.user.application.dtos.AuthLoginRequest;
import com.emazon.user.application.dtos.AuthResponse;
import com.emazon.user.application.handler.IAuthenticationHandler;
import com.emazon.user.domain.api.IAuthenticationServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationHandler implements IAuthenticationHandler {
    private final IAuthenticationServicePort authenticationServicePort;

    @Override
    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
        return new AuthResponse(authenticationServicePort.loginUser(authLoginRequest.getUsername(), authLoginRequest.getPassword()));
    }
}
