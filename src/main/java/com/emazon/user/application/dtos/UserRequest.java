package com.emazon.user.application.dtos;

import com.emazon.user.domain.utils.RoleEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequest extends UserBasicRequest{
    private RoleRequest roleRequest;
    public static UserRequest from(UserBasicRequest basicRequest, RoleRequest roleRequest) {
        UserRequest request = new UserRequest();
        request.setName(basicRequest.getName());
        request.setLastName(basicRequest.getLastName());
        request.setIdDocument(basicRequest.getIdDocument());
        request.setPhoneNumber(basicRequest.getPhoneNumber());
        request.setDateOfBirth(basicRequest.getDateOfBirth());
        request.setPassword(basicRequest.getPassword());
        request.setEmail(basicRequest.getEmail());
        request.setRoleRequest(roleRequest);
        return request;
    }
}
