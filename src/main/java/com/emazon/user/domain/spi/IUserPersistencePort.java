package com.emazon.user.domain.spi;

import com.emazon.user.domain.model.User;

public interface IUserPersistencePort {
    void saveUser(User user);

    boolean isEmailAlreadyInUse(String email);

    String hashPassword(String plainTextPassword);

    boolean isIdDocumentAlreadyInUse(String idDocument);
}
