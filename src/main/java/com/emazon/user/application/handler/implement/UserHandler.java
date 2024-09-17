package com.emazon.user.application.handler.implement;

import com.emazon.user.application.dtos.UserRequest;
import com.emazon.user.application.handler.IUserHandler;
import com.emazon.user.application.mapper.UserRequestMapper;
import com.emazon.user.domain.api.IUserServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {
    private final UserRequestMapper userRequestMapper;
    private final IUserServicePort userServicePort;

    @Override
    public void saveAuxUser(UserRequest userRequest) {
        userServicePort.saveAuxUser(
                userRequestMapper.toUser(userRequest)
        );
    }

    @Override
    public void saveClientUser(UserRequest userRequest) {
        userServicePort.saveClientUser(
                userRequestMapper.toUser(userRequest)
        );
    }

    @Override
    public Boolean isIdDocumentAvailable(String idDocument) {
        return userServicePort.isIdDocumentAvailable(idDocument);
    }

    @Override
    public Boolean isUserEmailAvailable(String email) {
        return userServicePort.isUserEmailAvailable(email);
    }
}
