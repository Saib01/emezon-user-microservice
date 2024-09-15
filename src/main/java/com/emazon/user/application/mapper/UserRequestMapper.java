package com.emazon.user.application.mapper;

import com.emazon.user.application.dtos.UserRequest;
import com.emazon.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.emazon.user.application.util.ApplicationConstants.*;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserRequestMapper {
    @Mapping(target = ROLE, source = ROLE_REQUEST)
    @Mapping(target = ROLE_DOT_ID, ignore = true)
    @Mapping(target = ID, ignore = true)
    User toUser(UserRequest userRequest);
}
