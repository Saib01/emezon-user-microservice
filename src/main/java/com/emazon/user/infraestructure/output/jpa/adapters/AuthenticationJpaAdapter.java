package com.emazon.user.infraestructure.output.jpa.adapters;

import com.emazon.user.domain.spi.IAuthenticationPersistencePort;
import com.emazon.user.infraestructure.configuration.jwt.JwtPayload;
import com.emazon.user.infraestructure.configuration.jwt.JwtUtils;
import com.emazon.user.infraestructure.output.jpa.entity.UserEntity;
import com.emazon.user.infraestructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Map;
import java.util.stream.Collectors;

import static com.emazon.user.application.dtos.JwtPayloadResponse.EMAIL_STRING;
import static com.emazon.user.application.dtos.JwtPayloadResponse.ID_STRING;
import static com.emazon.user.application.util.ApplicationConstants.ROLE;

@RequiredArgsConstructor
public class AuthenticationJpaAdapter implements IAuthenticationPersistencePort {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final JwtPayload jwtPayload;

    @Override
    public String generateToken(String username, String role) {
        Long idUser = userRepository.findUserEntityByEmail(username)
                .map(UserEntity::getId)
                .orElse(null);

        return jwtUtils.createToken(username, role, idUser);
    }

    @Override
    public String getRole(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );
        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());
    }

    @Override
    public Map<String, String> getJwtPayload() {
        return jwtPayload == null ?
                null
                : Map.of(ID_STRING, jwtPayload.getId(),
                EMAIL_STRING, jwtPayload.getEmail(),
                ROLE, jwtPayload.getRole()
        );
    }
}
