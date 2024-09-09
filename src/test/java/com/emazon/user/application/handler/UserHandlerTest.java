package com.emazon.user.application.handler;

import com.emazon.user.application.dtos.RoleRequest;
import com.emazon.user.application.dtos.UserBasicRequest;
import com.emazon.user.application.dtos.UserRequest;
import com.emazon.user.application.mapper.UserRequestMapper;
import com.emazon.user.domain.api.IUserServicePort;
import com.emazon.user.domain.model.Role;
import com.emazon.user.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.emazon.user.utils.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
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

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Role role = new Role(VALID_ID, VALID_USER_ROLE, VALID_USER_ROLE_DESCRIPTION);
        user = new User(VALID_USER_NAME, VALID_USER_LAST_NAME, VALID_USER_ID_DOCUMENT, VALID_USER_PHONE_NUMBER, VALID_USER_DATE_OF_BIRTH, VALID_USER_PASSWORD, VALID_USER_EMAIL);
        user.setId(VALID_ID);
        user.setRole(role);
    }

    @Test
    @DisplayName("Should save User correctly")
    void shouldSaveUser() {
        RoleRequest roleRequest = new RoleRequest(VALID_USER_ROLE, VALID_USER_ROLE_DESCRIPTION);
        UserBasicRequest userBasicRequest=new UserBasicRequest(VALID_USER_NAME, VALID_USER_LAST_NAME, VALID_USER_ID_DOCUMENT, VALID_USER_PHONE_NUMBER, VALID_USER_DATE_OF_BIRTH, VALID_USER_PASSWORD, VALID_USER_EMAIL);
        UserRequest userRequest = UserRequest.from(userBasicRequest,roleRequest);
        when(userRequestMapper.toUser(userRequest)).thenReturn(user);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        userHandler.saveAuxUser(userRequest);
        verify(userServicePort).saveUser(userCaptor.capture());
        assertEquals(userCaptor.getValue(), user);
    }
}