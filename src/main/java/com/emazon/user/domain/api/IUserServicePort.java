package com.emazon.user.domain.api;

import com.emazon.user.domain.model.User;

public interface IUserServicePort {
    void saveUser(User user);
}
