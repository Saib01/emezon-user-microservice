package com.emazon.user.application.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class UserBasicRequest {
    private String name;
    private String lastName;

    private String idDocument;
    private String phoneNumber;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;
    private String password;
    private String email;

    public UserBasicRequest(String name, String lastName, String idDocument, String phoneNumber, LocalDate dateOfBirth, String password, String email) {
        this.name = name;
        this.lastName = lastName;
        this.idDocument = idDocument;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.email = email;
    }

    UserBasicRequest() {

    }
}
