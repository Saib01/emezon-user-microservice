package com.emazon.user.infraestructure.output.jpa.adapters;

import com.emazon.user.domain.model.Role;
import com.emazon.user.infraestructure.output.jpa.entity.RoleEntity;
import com.emazon.user.infraestructure.output.jpa.mapper.RoleEntityMapper;
import com.emazon.user.infraestructure.output.jpa.repository.IRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.emazon.user.utils.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RoleJpaAdapterTest {


    @Mock
    private RoleEntityMapper roleEntityMapper;
    @InjectMocks
    private RoleJpaAdapter roleJpaAdapter;
    @Mock
    private IRoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should save the rol and verify that the rol repository method is called once")
    void saveRol() {
        RoleEntity roleEntity = new RoleEntity(VALID_ID, VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION);
        Role role = new Role(VALID_ID,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION);

        when(roleEntityMapper.toRoleEntity(role)).thenReturn(roleEntity);
        when(this.roleRepository.save(roleEntity)).thenReturn(roleEntity);

        Long idResult = roleJpaAdapter.saveRole(role);

        verify(roleEntityMapper, times(1)).toRoleEntity(role);
        assertThat(idResult).isEqualTo(roleEntity.getId());
    }
}