package com.emazon.user.infraestructure.output.jpa.adapters;

import com.emazon.user.infraestructure.configuration.jwt.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

import static com.emazon.user.utils.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthenticationJpaAdapterTest {

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationJpaAdapter authenticationJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should generate a valid JWT token when user email and role are correct")
    void testGenerateToken() {
        when(jwtUtils.createToken(VALID_USER_EMAIL, VALID_USER_FOR_TOKEN)).thenReturn(VALID_JWT_TOKEN);

        String actualToken = authenticationJpaAdapter.generateToken(VALID_USER_EMAIL, VALID_USER_FOR_TOKEN);

        assertEquals(VALID_JWT_TOKEN, actualToken);
        verify(jwtUtils, times(1)).createToken(VALID_USER_EMAIL, VALID_USER_FOR_TOKEN);
    }


    @Test
    @DisplayName("Should return correct role when user credentials are valid")

    void testGetRole() {
        Authentication authentication = new MockAuthentication(List.of(
                new SimpleGrantedAuthority(VALID_USER_FOR_TOKEN)
        ));

        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(VALID_USER_EMAIL, VALID_USER_FOR_TOKEN)))
                .thenReturn(authentication);

        String result = authenticationJpaAdapter.getRole(VALID_USER_EMAIL, VALID_USER_FOR_TOKEN);
        assertEquals(VALID_USER_FOR_TOKEN, result);
    }

    private static class MockAuthentication implements Authentication {
        private final Collection<? extends GrantedAuthority> authorities;

        public MockAuthentication(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = authorities;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public Object getCredentials() {
            return null;
        }

        @Override
        public Object getDetails() {
            return null;
        }

        @Override
        public Object getPrincipal() {
            return null;
        }

        @Override
        public boolean isAuthenticated() {
            return true;
        }

        @Override
        public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
            throw new UnsupportedOperationException(EMPTY_METHOD);
        }

        @Override
        public String getName() {
            return null;
        }
    }
}

