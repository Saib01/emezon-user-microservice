package com.emazon.user.domain.utils;

import java.io.Serializable;

public enum RoleEnum implements Serializable {
    ADMIN,
    AUX_BODEGA,
    CLIENT;

    @Override
    public String toString() {
        return name();
    }
}