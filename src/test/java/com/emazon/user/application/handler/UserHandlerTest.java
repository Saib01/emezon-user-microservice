package com.emazon.user.application.handler;

import com.emazon.user.application.dtos.UserBasicRequest;
import com.emazon.user.application.dtos.UserRequest;
import com.emazon.user.application.handler.implement.UserHandler;
import com.emazon.user.application.mapper.UserRequestMapper;
import com.emazon.user.domain.api.IUserServicePort;
import com.emazon.user.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.emazon.user.utils.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserHandlerTest {
    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private UserRequestMapper userRequestMapper;

    @InjectMocks
    private UserHandler userHandler;

    private User user;
    private UserRequest userRequest;
    ArgumentCaptor<User> userCaptor;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User(VALID_USER_NAME, VALID_USER_LAST_NAME, VALID_USER_ID_DOCUMENT, VALID_USER_PHONE_NUMBER, VALID_USER_DATE_OF_BIRTH, VALID_USER_PASSWORD, VALID_USER_EMAIL);
        user.setId(VALID_ID);
        userRequest = UserRequest.from(
                new UserBasicRequest(VALID_USER_NAME, VALID_USER_LAST_NAME, VALID_USER_ID_DOCUMENT, VALID_USER_PHONE_NUMBER, VALID_USER_DATE_OF_BIRTH, VALID_USER_PASSWORD, VALID_USER_EMAIL)
        );

        when(userRequestMapper.toUser(userRequest)).thenReturn(user);

        userCaptor = ArgumentCaptor.forClass(User.class);
    }

    @Test
    @DisplayName("Should save aux user correctly")
    void shouldSaveAuxUser() {
        userHandler.saveAuxUser(userRequest);
        verify(userServicePort).saveAuxUser(userCaptor.capture());
        assertEquals(userCaptor.getValue(), user);
    }
    @Test
    @DisplayName("Should save client user correctly")
    void shouldSaveClientUser() {
        userHandler.saveClientUser(userRequest);
        verify(userServicePort).saveClientUser(userCaptor.capture());
        assertEquals(userCaptor.getValue(), user);
    }
}