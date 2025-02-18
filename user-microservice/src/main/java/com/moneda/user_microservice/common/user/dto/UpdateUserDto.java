package com.moneda.user_microservice.common.user.dto;

import com.moneda.user_microservice.common.documentType.entity.DocumentType;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data

public class UpdateUserDto {

    private String firstName;
    private String lastName;
    private String address;
    private Date birthDate;
    private String phone;




}
