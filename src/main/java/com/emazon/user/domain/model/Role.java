package com.emazon.user.domain.model;

import com.emazon.user.domain.utils.RoleEnum;

public class Role {
    private Long id;
    private RoleEnum roleEnum;
    private String description;

    public Role(Long id, RoleEnum roleEnum, String description) {
        this.id = id;
        this.roleEnum = roleEnum;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }
}

