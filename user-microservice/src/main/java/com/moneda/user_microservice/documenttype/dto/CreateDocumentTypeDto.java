package com.moneda.user_microservice.documenttype.dto;

import lombok.Data;

@Data
public class CreateDocumentTypeDto {

    private String code;
    private String name;
    private String countryCode;
}
