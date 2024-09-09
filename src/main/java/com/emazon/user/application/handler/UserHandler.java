package com.emazon.user.application.handler;

import com.emazon.user.application.dtos.UserRequest;
import com.emazon.user.application.mapper.UserRequestMapper;
import com.emazon.user.domain.api.IUserServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler{
    private final UserRequestMapper userRequestMapper;
    private final IUserServicePort userServicePort;
    @Override
    public void saveUser(UserRequest userRequest) {
        userServicePort.saveUser(
                userRequestMapper.toUser(userRequest)
        );
    }
}
