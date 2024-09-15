package com.emazon.user.domain.spi;

import com.emazon.user.domain.model.Role;
import com.emazon.user.domain.utils.RoleEnum;

public interface IRolePersistencePort {
    Long saveRole(Role role);

    Role findByRoleEnum(RoleEnum roleEnum);
}
