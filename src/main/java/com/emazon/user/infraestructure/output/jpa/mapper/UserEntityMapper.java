package com.emazon.user.infraestructure.output.jpa.mapper;

import com.emazon.user.domain.model.User;
import com.emazon.user.infraestructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.emazon.user.application.util.ApplicationConstants.ROLE;
import static com.emazon.user.infraestructure.util.InfrastructureEntities.ROLE_ENTITY;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,uses = {RoleEntityMapper.class})
public interface UserEntityMapper {
    @Mapping(source = ROLE,target = ROLE_ENTITY)
    UserEntity toUserEntity(User user);
    @Mapping(target =ROLE,source = ROLE_ENTITY)
    User toUser(UserEntity userEntity);
}