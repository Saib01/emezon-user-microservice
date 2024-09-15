package com.emazon.user.infraestructure.output.jpa.mapper;


import com.emazon.user.domain.model.Role;
import com.emazon.user.infraestructure.output.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface RoleEntityMapper {

    RoleEntity toRoleEntity(Role role);

    Role toRole(RoleEntity roleEntity);
}