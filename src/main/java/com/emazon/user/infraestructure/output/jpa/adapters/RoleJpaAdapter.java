package com.emazon.user.infraestructure.output.jpa.adapters;

import com.emazon.user.domain.model.Role;
import com.emazon.user.domain.spi.IRolePersistencePort;
import com.emazon.user.domain.utils.RoleEnum;
import com.emazon.user.infraestructure.output.jpa.mapper.RoleEntityMapper;
import com.emazon.user.infraestructure.output.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;
    @Override
    public Long saveRole(Role role) {
        return this.roleRepository.save(
                roleEntityMapper.toRoleEntity(role)
        ).getId();
    }

    @Override
    public Role findByRoleEnum(RoleEnum roleEnum) {
        return this.roleEntityMapper.toRole(
                this.roleRepository.findByRoleEnum(roleEnum).orElse(null)
        );
    }
}
