package com.emazon.user.infraestructure.configuration.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

import static com.emazon.user.infraestructure.util.InfrastructureConstants.AUTHORITIES;
import static com.emazon.user.infraestructure.util.InfrastructureConstants.BEARER_PREFIX;
import static org.springframework.data.jpa.domain.AbstractPersistable_.ID;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final JwtPayload jwtPayload;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
            String jwtToken = authHeader.substring(BEARER_PREFIX.length());
            DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

            if (decodedJWT != null) {
                jwtPayload.setId(jwtUtils.getSpecificClaim(decodedJWT, ID).toString())
                        .setRole(jwtUtils.getSpecificClaim(decodedJWT, AUTHORITIES).asString())
                        .setEmail(jwtUtils.extractUsername(decodedJWT));
                Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(jwtPayload.getRole());

                SecurityContext context = SecurityContextHolder.getContext();
                Authentication authentication = new UsernamePasswordAuthenticationToken(jwtPayload.getId(), null, authorities);
                context.setAuthentication(authentication);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request, response);
    }
}
