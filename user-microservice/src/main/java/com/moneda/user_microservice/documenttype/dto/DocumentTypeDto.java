package com.moneda.user_microservice.documenttype.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTypeDto {

    private UUID id;
    private String code;
    private String name;
    private String countryCode;


}
