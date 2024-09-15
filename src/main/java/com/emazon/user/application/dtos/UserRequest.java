package com.emazon.user.application.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequest extends UserBasicRequest {
    private RoleRequest roleRequest;

    public UserRequest(String name, String lastName, String idDocument, String phoneNumber, LocalDate dateOfBirth, String password, String email) {
        super(name, lastName, idDocument, phoneNumber, dateOfBirth, password, email);
    }

    private UserRequest() {
        super();
    }

    public static UserRequest from(UserBasicRequest basicRequest, RoleRequest roleRequest) {
        UserRequest request = UserRequest.from(basicRequest);
        request.setRoleRequest(roleRequest);
        return request;
    }

    public static UserRequest from(UserBasicRequest basicRequest) {
        UserRequest request = new UserRequest();
        request.setName(basicRequest.getName());
        request.setLastName(basicRequest.getLastName());
        request.setIdDocument(basicRequest.getIdDocument());
        request.setPhoneNumber(basicRequest.getPhoneNumber());
        request.setDateOfBirth(basicRequest.getDateOfBirth());
        request.setPassword(basicRequest.getPassword());
        request.setEmail(basicRequest.getEmail());
        return request;
    }
}
