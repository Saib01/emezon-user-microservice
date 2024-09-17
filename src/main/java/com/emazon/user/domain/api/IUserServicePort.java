package com.emazon.user.domain.api;

import com.emazon.user.domain.model.User;
import com.emazon.user.domain.utils.RoleEnum;

public interface IUserServicePort {
    void saveUser(User user, RoleEnum roleEnum, String description);

    void saveAuxUser(User user);

    void saveClientUser(User user);

    Boolean isUserEmailAvailable(String email);

    Boolean isIdDocumentAvailable(String idDocument);
}
