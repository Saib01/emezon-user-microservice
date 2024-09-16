package com.emazon.user.application.mapper;

import com.emazon.user.application.dtos.RoleRequest;
import com.emazon.user.application.dtos.UserBasicRequest;
import com.emazon.user.application.dtos.UserRequest;
import com.emazon.user.domain.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static com.emazon.user.utils.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

class UserRequestMapperTest {
    private final UserRequestMapper userRequestMapper = Mappers.getMapper(UserRequestMapper.class);

    @Test
    @DisplayName("Should map UserRequest to User correctly")
    void shouldMapUserRequestToUser() {

        RoleRequest roleRequest = new RoleRequest(VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION);
        UserBasicRequest userBasicRequest = new UserBasicRequest(VALID_USER_NAME, VALID_USER_LAST_NAME, VALID_USER_ID_DOCUMENT, VALID_USER_PHONE_NUMBER, VALID_USER_DATE_OF_BIRTH, VALID_USER_PASSWORD, VALID_USER_EMAIL);
        UserRequest userRequest = UserRequest.from(userBasicRequest, roleRequest);
        User result = userRequestMapper.toUser(userRequest);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(userRequest.getName());
        assertThat(result.getLastName()).isEqualTo(userRequest.getLastName());
        assertThat(result.getIdDocument()).isEqualTo(userRequest.getIdDocument());
        assertThat(result.getPhoneNumber()).isEqualTo(userRequest.getPhoneNumber());
        assertThat(result.getDateOfBirth()).isEqualTo(userRequest.getDateOfBirth());
        assertThat(result.getPassword()).isEqualTo(userRequest.getPassword());
        assertThat(result.getEmail()).isEqualTo(userRequest.getEmail());
    }
}