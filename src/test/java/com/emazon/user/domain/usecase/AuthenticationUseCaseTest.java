package com.emazon.user.domain.usecase;


import com.emazon.user.domain.exeption.user.UserBadCredentialsException;
import com.emazon.user.domain.spi.IAuthenticationPersistencePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static com.emazon.user.application.dtos.JwtPayloadResponse.EMAIL_STRING;
import static com.emazon.user.application.dtos.JwtPayloadResponse.ID_STRING;
import static com.emazon.user.application.util.ApplicationConstants.ROLE;
import static com.emazon.user.domain.utils.RoleEnum.ADMIN;
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



    @Test
    @DisplayName("Should return the jwt payload in a map")
    void shouldReturnJwtPayloadInAMap() {
        Map<String,String> jwtPayloadMap= Map.of(
                ID_STRING, VALID_ID.toString(),
                EMAIL_STRING, VALID_USER_EMAIL,
                ROLE, ADMIN.toString()
        );
        when(this.authenticationPersistencePort.getJwtPayload()).thenReturn(jwtPayloadMap);

        Map<String,String> result=this.authenticationUseCase.getJwtPayload();
        Assertions.assertEquals(jwtPayloadMap,result);
    }

}