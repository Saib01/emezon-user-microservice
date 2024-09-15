package com.emazon.user.domain.usecase;


import com.emazon.user.domain.exeption.user.UserBadCredentialsException;
import com.emazon.user.domain.spi.IAuthenticationPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.emazon.user.utils.TestConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthenticationUseCaseTest {

    @Mock
    private IAuthenticationPersistencePort authenticationPersistencePort;

    @InjectMocks
    private AuthenticationUseCase authenticationUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return JWT token when login is successful")
    void shouldReturnJwtTokenWhenLoginIsSuccessful(){

        when(authenticationPersistencePort.getRole(VALID_USER_EMAIL, VALID_USER_PASSWORD)).thenReturn(VALID_USER_FOR_TOKEN);
        when(authenticationPersistencePort.generateToken(VALID_USER_EMAIL, VALID_USER_FOR_TOKEN)).thenReturn(VALID_JWT_TOKEN);

        String actualToken = authenticationUseCase.loginUser(VALID_USER_EMAIL, VALID_USER_PASSWORD);

        assertEquals(VALID_JWT_TOKEN, actualToken);

        verify(authenticationPersistencePort).getRole(VALID_USER_EMAIL, VALID_USER_PASSWORD);
        verify(authenticationPersistencePort).generateToken(VALID_USER_EMAIL, VALID_USER_FOR_TOKEN);
    }

    @Test
    @DisplayName("Should throw UserBadCredentialsException when login credentials are invalid")
    void shouldThrowUserBadCredentialsExceptionWhenLoginCredentialsAreInvalid(){

        when(authenticationPersistencePort.getRole(VALID_USER_EMAIL, INVALID_USER_PASSWORD)).thenThrow(new RuntimeException());

        assertThrows(UserBadCredentialsException.class, () -> {
            authenticationUseCase.loginUser(VALID_USER_EMAIL, INVALID_USER_PASSWORD);
        });
    }

}