package com.emazon.user.infraestructure.output.jpa.adapters;

import com.emazon.user.domain.spi.IAuthenticationPersistencePort;
import com.emazon.user.infraestructure.configuration.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AuthenticationJpaAdapter implements IAuthenticationPersistencePort {

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    @Override
    public String generateToken(String username,String role){
        return jwtUtils.createToken(username,role);
    }

    @Override
    public String getRole(String username, String password) {
        Authentication authentication=authenticationManager.authenticate(
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
}
