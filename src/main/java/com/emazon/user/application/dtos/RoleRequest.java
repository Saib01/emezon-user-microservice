package com.emazon.user.application.dtos;

import com.emazon.user.domain.utils.RoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequest {
    private RoleEnum roleEnum;
    private String description;

    public RoleRequest(RoleEnum roleEnum, String description) {
        this.roleEnum = roleEnum;
        this.description = description;
    }
}
