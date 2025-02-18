package com.emazon.user.infraestructure.configuration;

import com.emazon.user.domain.utils.RoleEnum;
import com.emazon.user.infraestructure.configuration.jwt.JwtAuthenticationFilter;
import com.emazon.user.infraestructure.configuration.jwt.JwtPayload;
import com.emazon.user.infraestructure.configuration.jwt.JwtUtils;
import com.emazon.user.infraestructure.exceptionhandler.CustomAccessDeniedHandler;
import com.emazon.user.infraestructure.exceptionhandler.JwtAuthenticationEntryPoint;
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

import static com.emazon.user.infraestructure.util.InfraestructureRestControllerConstants.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtUtils jwtUtils;
    private final JwtPayload jwtPayload;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    http.requestMatchers(HttpMethod.POST, API_SIGNUP_AUX_PATH).hasRole(RoleEnum.ADMIN.name());
                    http.requestMatchers(HttpMethod.GET, API_VALIDATE_EMAIL, API_VALIDATE_ID_DOCUMENT).permitAll();
                    http.requestMatchers(HttpMethod.POST, API_SIGNUP_CLIENT_PATH, API_AUTH_PATH).permitAll();
                    http.anyRequest().authenticated();

                })
                .addFilterBefore(new JwtAuthenticationFilter(jwtUtils, jwtPayload), BasicAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider)
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(customAccessDeniedHandler)
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .build();
    }

}
