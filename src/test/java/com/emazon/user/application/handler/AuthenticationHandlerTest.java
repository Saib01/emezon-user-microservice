package com.emazon.user.application.handler;

import com.emazon.user.application.dtos.AuthLoginRequest;
import com.emazon.user.application.dtos.AuthResponse;
import com.emazon.user.application.handler.implement.AuthenticationHandler;
import com.emazon.user.domain.api.IAuthenticationServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.emazon.user.utils.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthenticationHandlerTest {
    @Mock
    private IAuthenticationServicePort authenticationServicePort;
    @InjectMocks
    private AuthenticationHandler authenticationHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should call loginUser and return a valid AuthResponse")
    void loginUserTest() {

        AuthLoginRequest request = new AuthLoginRequest(VALID_USER_EMAIL, VALID_USER_PASSWORD);

        when(authenticationServicePort.loginUser(request.getUsername(), request.getPassword())).thenReturn(VALID_JWT_TOKEN);

        AuthResponse response = authenticationHandler.loginUser(request);


        ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);

        verify(authenticationServicePort, times(1)).loginUser(usernameCaptor.capture(), passwordCaptor.capture());

        assertEquals(VALID_USER_EMAIL, usernameCaptor.getValue());
        assertEquals(VALID_USER_PASSWORD, passwordCaptor.getValue());
        assertEquals(VALID_JWT_TOKEN, response.getToken());
    }
}
