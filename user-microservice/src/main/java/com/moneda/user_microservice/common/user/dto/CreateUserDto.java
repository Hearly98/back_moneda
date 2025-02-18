package com.moneda.user_microservice.common.user.dto;

import com.moneda.user_microservice.common.documentType.dto.DocumentTypeDto;
import com.moneda.user_microservice.common.documentType.entity.DocumentType;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data

public class CreateUserDto {

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
