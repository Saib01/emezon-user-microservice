package com.emazon.user.infraestructure.output.jpa.mapper;

import com.emazon.user.domain.model.Role;
import com.emazon.user.infraestructure.output.jpa.entity.RoleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.MockitoAnnotations;

import static com.emazon.user.utils.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

class RoleEntityMapperTest {

    private final RoleEntityMapper roleEntityMapper = Mappers.getMapper(RoleEntityMapper.class);
    RoleEntity roleEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        roleEntity = new RoleEntity(VALID_ID, VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION);
    }


    @Test
    @DisplayName("Should map roleEntity to role correctly")
    void toRole() {
        Role result = roleEntityMapper.toRole(roleEntity);

        assertRoleEqual(result, roleEntity);
    }

    @Test
    @DisplayName("Should map role to roleEntity correctly")
    void toRoleEntity() {
        Role role = new Role(VALID_ID, VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION);
        RoleEntity result = roleEntityMapper.toRoleEntity(role);

        assertRoleEqual(role, result);
    }

    private void assertRoleEqual(Role role, RoleEntity roleEntity) {
        assertThat(role.getId()).isEqualTo(roleEntity.getId());
        assertThat(role.getRoleEnum()).isEqualTo(roleEntity.getRoleEnum());
        assertThat(role.getDescription()).isEqualTo(roleEntity.getDescription());
    }
}