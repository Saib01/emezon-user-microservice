package com.emazon.user.infraestructure.output.jpa.adapters;

import com.emazon.user.domain.model.Role;
import com.emazon.user.domain.model.User;
import com.emazon.user.infraestructure.output.jpa.entity.RoleEntity;
import com.emazon.user.infraestructure.output.jpa.entity.UserEntity;
import com.emazon.user.infraestructure.output.jpa.mapper.UserEntityMapper;
import com.emazon.user.infraestructure.output.jpa.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.emazon.user.utils.TestConstants.*;
import static com.emazon.user.utils.TestConstants.VALID_USER_ROLE_DESCRIPTION;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserJpaAdapterTest {

    @Mock
    private UserEntityMapper userEntityMapper ;
    @InjectMocks
    private  UserJpaAdapter userJpaAdapter;
    @Mock
    private IUserRepository userRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("Should save the user and verify that the user repository method is called once")
    void saveUser() {
        RoleEntity roleEntity = new RoleEntity(VALID_ID, VALID_USER_ROLE, VALID_USER_ROLE_DESCRIPTION);
        UserEntity userEntity = new UserEntity(VALID_ID,VALID_USER_NAME,VALID_USER_LAST_NAME,VALID_USER_ID_DOCUMENT,
                VALID_USER_PHONE_NUMBER,VALID_USER_DATE_OF_BIRTH,VALID_USER_PASSWORD,VALID_USER_EMAIL,
                roleEntity);
        User user=new User(VALID_USER_NAME,VALID_USER_LAST_NAME,VALID_USER_ID_DOCUMENT,
                VALID_USER_PHONE_NUMBER,VALID_USER_DATE_OF_BIRTH,VALID_USER_PASSWORD,VALID_USER_EMAIL);
        user.setId(VALID_ID);
        user.setRole(new Role(VALID_ID,VALID_USER_ROLE,VALID_USER_ROLE_DESCRIPTION));
        when(userEntityMapper.toUserEntity(user)).thenReturn(userEntity);
        userJpaAdapter.saveUser(user);

        ArgumentCaptor<UserEntity> userEntityCaptor= ArgumentCaptor.forClass(UserEntity.class);
        verify(userEntityMapper, times(1)).toUserEntity(user);
        verify(userRepository, times(1)).save(userEntityCaptor.capture());
        assertEquals(userEntityCaptor.getValue(),userEntity);
    }
}