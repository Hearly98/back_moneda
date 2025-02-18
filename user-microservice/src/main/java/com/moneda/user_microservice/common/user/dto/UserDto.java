package com.moneda.user_microservice.common.user.dto;

import com.moneda.user_microservice.common.documentType.entity.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String firstName;
    private String lastName;
    private String address;
    private String numDocument;
    private Date birthDate;
    private String phone;
    private String email;
    private String password;
    private String countryCode;
    private UUID documentTypeId;







}
