package com.emazon.user.domain.usecase;

import com.emazon.user.domain.api.IUserServicePort;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.spi.IRolePersistencePort;
import com.emazon.user.domain.spi.IUserPersistencePort;
import com.emazon.user.domain.utils.Validator;


public class UserUseCase implements IUserServicePort {

    private final IRolePersistencePort rolePersistencePort;
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort,IRolePersistencePort rolePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort=rolePersistencePort;
    }


    @Override
    public void saveUser(User user) {
        Validator.validateUserProperties(user,rolePersistencePort);
        Validator.validateIdDocumentIsAlreadyInUse(user,userPersistencePort);
        Validator.validateEmailIsAlreadyInUse(user,userPersistencePort);
        user.setPassword(this.userPersistencePort.hashPassword(user.getPassword()));
        this.userPersistencePort.saveUser(user);
    }
}
