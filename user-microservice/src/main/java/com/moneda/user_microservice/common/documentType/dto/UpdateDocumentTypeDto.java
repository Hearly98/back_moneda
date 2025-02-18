package com.moneda.user_microservice.common.documentType.dto;

import lombok.Data;

@Data
public class UpdateDocumentTypeDto {

    private String code;
    private String name;
    private String countryCode;

}
