package com.emazon.user.application.handler;

import com.emazon.user.application.dtos.UserRequest;

public interface IUserHandler {
    void saveAuxUser(UserRequest userRequest);
}
