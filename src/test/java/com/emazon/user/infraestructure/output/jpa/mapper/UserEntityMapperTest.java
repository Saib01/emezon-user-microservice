package com.emazon.user.infraestructure.output.jpa.mapper;

import com.emazon.user.domain.model.Role;
import com.emazon.user.domain.model.User;
import com.emazon.user.infraestructure.output.jpa.entity.RoleEntity;
import com.emazon.user.infraestructure.output.jpa.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.emazon.user.utils.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserEntityMapperTest {
    UserEntity userEntity;
    @Autowired
    private RoleEntityMapper roleEntityMapper;
    @Autowired
    private UserEntityMapper userEntityMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        RoleEntity roleEntity = new RoleEntity(VALID_ID, VALID_USER_ROLE, VALID_USER_ROLE_DESCRIPTION);
        userEntity = new UserEntity(VALID_ID, VALID_USER_NAME, VALID_USER_LAST_NAME, VALID_USER_ID_DOCUMENT,
                VALID_USER_PHONE_NUMBER, VALID_USER_DATE_OF_BIRTH, VALID_USER_PASSWORD, VALID_USER_EMAIL,
                roleEntity
        );
    }


    @Test
    @DisplayName("Should map userEntity to user correctly")
    void toUser() {
        User result = userEntityMapper.toUser(userEntity);

        assertUserEqual(result, userEntity);
    }

    @Test
    @DisplayName("Should map user to userEntity correctly")
    void toUserEntity() {
        User user = new User(VALID_USER_NAME, VALID_USER_LAST_NAME, VALID_USER_ID_DOCUMENT,
                VALID_USER_PHONE_NUMBER, VALID_USER_DATE_OF_BIRTH, VALID_USER_PASSWORD, VALID_USER_EMAIL);
        user.setId(VALID_ID);
        user.setRole(new Role(VALID_ID, VALID_USER_ROLE, VALID_USER_ROLE_DESCRIPTION));
        UserEntity result = userEntityMapper.toUserEntity(user);

        assertUserEqual(user, result);
    }

    private void assertUserEqual(User user, UserEntity userEntity) {
        assertThat(user.getId()).isEqualTo(userEntity.getId());
        assertThat(user.getName()).isEqualTo(userEntity.getName());
        assertThat(user.getLastName()).isEqualTo(userEntity.getLastName());
        assertThat(user.getIdDocument()).isEqualTo(userEntity.getIdDocument());
        assertThat(user.getPhoneNumber()).isEqualTo(userEntity.getPhoneNumber());
        assertThat(user.getDateOfBirth()).isEqualTo(userEntity.getDateOfBirth());
        assertThat(user.getPassword()).isEqualTo(userEntity.getPassword());
        assertThat(user.getEmail()).isEqualTo(userEntity.getEmail());
        assertThat(user.getRole().getId()).isEqualTo(userEntity.getRoleEntity().getId());
        assertThat(user.getRole().getRoleEnum()).isEqualTo(userEntity.getRoleEntity().getRoleEnum());
        assertThat(user.getRole().getDescription()).isEqualTo(userEntity.getRoleEntity().getDescription());
    }
}
