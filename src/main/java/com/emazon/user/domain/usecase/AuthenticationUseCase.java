package com.emazon.user.domain.usecase;

import com.emazon.user.domain.api.IAuthenticationServicePort;
import com.emazon.user.domain.exeption.user.UserBadCredentialsException;
import com.emazon.user.domain.spi.IAuthenticationPersistencePort;

import java.util.Map;

import static com.emazon.user.domain.exeption.ExceptionResponseDomain.USER_PASSWORD_INCORRECT;

public class AuthenticationUseCase implements IAuthenticationServicePort {
    private final IAuthenticationPersistencePort authenticationPersistencePort;

    public AuthenticationUseCase(IAuthenticationPersistencePort authenticationPersistencePort) {
        this.authenticationPersistencePort = authenticationPersistencePort;
    }

    @Override
    public String loginUser(String username, String password) {
        try {
            return this.authenticationPersistencePort.generateToken(username, authenticationPersistencePort.getRole(username, password));
        } catch (RuntimeException e) {
            throw new UserBadCredentialsException(USER_PASSWORD_INCORRECT.getMessage());
        }
    }

    @Override
    public Map<String, String> getJwtPayload() {
        return this.authenticationPersistencePort.getJwtPayload();
    }
}
