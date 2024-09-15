package com.emazon.user.domain.api;

public interface IAuthenticationServicePort {
    String loginUser(String username, String password);
}
