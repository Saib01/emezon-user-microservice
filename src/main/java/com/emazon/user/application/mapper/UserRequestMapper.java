package com.emazon.user.application.mapper;

import com.emazon.user.application.dtos.UserRequest;
import com.emazon.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {
    @Mapping(target = "role",source = "roleRequest")
    @Mapping(target = "role.id",ignore = true)
    @Mapping(target="id",ignore = true)
    User toUser(UserRequest userRequest);
}
