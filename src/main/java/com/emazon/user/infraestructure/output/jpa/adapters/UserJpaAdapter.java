package com.emazon.user.infraestructure.output.jpa.adapters;

import com.emazon.user.domain.model.User;
import com.emazon.user.domain.spi.IUserPersistencePort;
import com.emazon.user.infraestructure.output.jpa.mapper.UserEntityMapper;
import com.emazon.user.infraestructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {
        this.userRepository.save(
                this.userEntityMapper.toUserEntity(user)
        );
    }

    @Override
    public boolean isEmailAlreadyInUse(String email) {
        return !userRepository.findUserEntityByEmail(email).isEmpty();
    }


    @Override
    public String hashPassword(String plainTextPassword) {
        return passwordEncoder.encode(plainTextPassword);
    }

    @Override
    public boolean isIdDocumentAlreadyInUse(String idDocument) {
        return !userRepository.findUserEntityByIdDocument(idDocument).isEmpty();
    }
}
