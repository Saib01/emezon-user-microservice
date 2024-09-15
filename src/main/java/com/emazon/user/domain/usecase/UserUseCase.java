package com.emazon.user.domain.usecase;

import com.emazon.user.domain.api.IUserServicePort;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.spi.IRolePersistencePort;
import com.emazon.user.domain.spi.IUserPersistencePort;
import com.emazon.user.domain.utils.RoleEnum;


import static com.emazon.user.domain.utils.DomainConstants.AUX_ROLE_DESCRIPTION;
import static com.emazon.user.domain.utils.DomainConstants.CLIENT_ROLE_DESCRIPTION;
import static com.emazon.user.domain.utils.UserValidator.*;


public class UserUseCase implements IUserServicePort {

    private final IRolePersistencePort rolePersistencePort;
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
    }


    @Override
    public void saveUser(User user, RoleEnum roleEnum, String roleDescription) {
        validateProperties(user);
        validateIdDocumentIsAlreadyInUse(user, userPersistencePort);
        validateEmailIsAlreadyInUse(user, userPersistencePort);

        user.setRole(getRole(roleEnum,roleDescription, rolePersistencePort));
        user.setPassword(this.userPersistencePort.hashPassword(user.getPassword()));

        this.userPersistencePort.saveUser(user);
    }

    @Override
    public void saveAuxUser(User user) {
        saveUser(user, RoleEnum.AUX_BODEGA, AUX_ROLE_DESCRIPTION);
    }

    @Override
    public void saveClientUser(User user) {
        saveUser(user, RoleEnum.CLIENT, CLIENT_ROLE_DESCRIPTION);
    }
}
