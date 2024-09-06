package com.emazon.user.infraestructure.output.jpa.repository;

import com.emazon.user.domain.utils.RoleEnum;
import com.emazon.user.infraestructure.output.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<RoleEntity,Long> {
    Optional<RoleEntity> findByRoleEnum(RoleEnum roleEnum);
}
