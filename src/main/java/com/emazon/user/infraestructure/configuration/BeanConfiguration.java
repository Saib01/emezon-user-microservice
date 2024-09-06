package com.emazon.user.infraestructure.configuration;

import com.emazon.user.domain.api.IUserServicePort;
import com.emazon.user.domain.spi.IRolePersistencePort;
import com.emazon.user.domain.spi.IUserPersistencePort;
import com.emazon.user.domain.usecase.UserUseCase;
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

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

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
        return  new BCryptPasswordEncoder();
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
                                new SimpleGrantedAuthority("ROLE_" + userEntity.getRoleEntity().getRoleEnum().name()))
                        )
                )
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
