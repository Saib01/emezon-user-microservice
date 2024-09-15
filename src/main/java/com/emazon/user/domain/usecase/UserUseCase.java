package com.emazon.user.domain.usecase;

import com.emazon.user.domain.api.IUserServicePort;
import com.emazon.user.domain.model.Role;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.spi.IRolePersistencePort;
import com.emazon.user.domain.spi.IUserPersistencePort;
import com.emazon.user.domain.utils.RoleEnum;
import com.emazon.user.domain.utils.UserValidator;

import static com.emazon.user.domain.utils.DomainConstants.AUX_USER_DESCRIPTION;


public class UserUseCase implements IUserServicePort {

    private final IRolePersistencePort rolePersistencePort;
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
    }


    @Override
    public void saveUser(User user) {
        UserValidator.validateProperties(user, rolePersistencePort);
        UserValidator.validateIdDocumentIsAlreadyInUse(user, userPersistencePort);
        UserValidator.validateEmailIsAlreadyInUse(user, userPersistencePort);
        user.setPassword(this.userPersistencePort.hashPassword(user.getPassword()));
        this.userPersistencePort.saveUser(user);
    }

    @Override
    public void saveAuxUser(User user) {
        user.setRole(new Role(null, RoleEnum.AUX_BODEGA, AUX_USER_DESCRIPTION));
        this.saveUser(user);
    }
}
