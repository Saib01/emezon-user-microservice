package com.emazon.user.infraestructure.configuration;

import com.emazon.user.domain.api.IAuthenticationServicePort;
import com.emazon.user.domain.api.IUserServicePort;
import com.emazon.user.domain.spi.IAuthenticationPersistencePort;
import com.emazon.user.domain.spi.IRolePersistencePort;
import com.emazon.user.domain.spi.IUserPersistencePort;
import com.emazon.user.domain.usecase.AuthenticationUseCase;
import com.emazon.user.domain.usecase.UserUseCase;
import com.emazon.user.infraestructure.configuration.jwt.JwtUtils;
import com.emazon.user.domain.exeption.ExceptionResponseDomain;
import com.emazon.user.infraestructure.output.jpa.adapters.AuthenticationJpaAdapter;
import com.emazon.user.infraestructure.output.jpa.adapters.RoleJpaAdapter;
import com.emazon.user.infraestructure.output.jpa.adapters.UserJpaAdapter;
import com.emazon.user.infraestructure.output.jpa.mapper.RoleEntityMapper;
import com.emazon.user.infraestructure.output.jpa.mapper.UserEntityMapper;
import com.emazon.user.infraestructure.output.jpa.repository.IRoleRepository;
import com.emazon.user.infraestructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static com.emazon.user.domain.utils.DomainConstants.ROLE_PREFIX;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;
    private final JwtUtils jwtUtils;

    @Bean
    IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper, encoder());
    }

    @Bean
    IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), rolePersistencePort());
    }

    @Bean
    IRolePersistencePort rolePersistencePort() {
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    IAuthenticationPersistencePort authenticationPersistencePort() throws Exception {
        return new AuthenticationJpaAdapter(jwtUtils, authenticationManager(null));
    }

    @Bean
    IAuthenticationServicePort authenticationServicePort() throws Exception {
        return new AuthenticationUseCase(authenticationPersistencePort());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(encoder());
        return provider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findUserEntityByEmail(username)
                .map(userEntity -> new User(
                                userEntity.getUsername(),
                                userEntity.getPassword(),
                                userEntity.isEnabled(),
                                userEntity.isAccountNonExpired(),
                                userEntity.isCredentialsNonExpired(),
                                userEntity.isAccountNonLocked(),
                                List.of(
                                        new SimpleGrantedAuthority(ROLE_PREFIX + userEntity.getRoleEntity().getRoleEnum().name()))
                        )
                )
                .orElseThrow(() -> new UsernameNotFoundException(ExceptionResponseDomain.USER_NOT_FOUND.getMessage()));
    }

}
