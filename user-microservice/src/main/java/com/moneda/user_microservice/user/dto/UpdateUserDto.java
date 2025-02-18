package com.moneda.user_microservice.user.dto;

import lombok.Data;

import java.util.Date;

@Data

public class UpdateUserDto {

    private String firstName;
    private String lastName;
    private String address;
    private Date birthDate;
    private String phone;




}
