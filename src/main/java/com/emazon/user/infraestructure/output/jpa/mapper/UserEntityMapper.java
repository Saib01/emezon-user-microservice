package com.emazon.user.infraestructure.output.jpa.mapper;

import com.emazon.user.domain.model.User;
import com.emazon.user.infraestructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {RoleEntityMapper.class})
public interface UserEntityMapper {
    @Mapping(source = "role",target = "roleEntity")
    UserEntity toUserEntity(User user);
    @Mapping(target = "role",source = "roleEntity")
    User toUser(UserEntity userEntity);
}