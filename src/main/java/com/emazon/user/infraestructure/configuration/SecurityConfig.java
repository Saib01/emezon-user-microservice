package com.emazon.user.infraestructure.configuration;

import com.emazon.user.domain.utils.RoleEnum;
import com.emazon.user.infraestructure.configuration.jwt.JwtAuthenticationFilter;
import com.emazon.user.infraestructure.configuration.jwt.JwtUtils;
import com.emazon.user.infraestructure.exceptionhandler.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static com.emazon.user.infraestructure.util.InfraestructureRestControllerConstants.API_AUTH_PATH;
import static com.emazon.user.infraestructure.util.InfraestructureRestControllerConstants.API_SIGNUP_PATH;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtUtils jwtUtils;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    http.requestMatchers(HttpMethod.POST, API_SIGNUP_PATH).hasRole(RoleEnum.ADMIN.name());
                    http.requestMatchers(HttpMethod.POST, API_AUTH_PATH).permitAll();
                    http.anyRequest().permitAll();
                })
                .addFilterBefore(new JwtAuthenticationFilter(jwtUtils), BasicAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider)
                .exceptionHandling(exceptionHandling ->exceptionHandling
                        .accessDeniedHandler(customAccessDeniedHandler))
                .build();
    }

}
