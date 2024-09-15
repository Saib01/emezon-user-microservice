package com.emazon.user.infraestructure.output.jpa.repository;

import com.emazon.user.infraestructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserEntityByEmail(String name);

    Optional<UserEntity> findUserEntityByIdDocument(String idDocument);
}
