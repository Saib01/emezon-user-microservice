package com.emazon.user.infraestructure.output.jpa.entity;


import com.emazon.user.domain.utils.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

import static com.emazon.user.infraestructure.util.InfrastructureEntities.ID_ROLE;
import static com.emazon.user.infraestructure.util.InfrastructureEntities.TABLE_ROLES;


@Entity
@Table(name = TABLE_ROLES)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_ROLE, nullable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;
    @Column(nullable = false)
    private String description;

}
