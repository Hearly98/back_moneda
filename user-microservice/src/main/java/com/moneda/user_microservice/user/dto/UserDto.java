package com.moneda.user_microservice.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String numDocument;
    private Date birthDate;
    private String phone;
    private String email;
    private String countryCode;
    private UUID documentTypeId;







}
