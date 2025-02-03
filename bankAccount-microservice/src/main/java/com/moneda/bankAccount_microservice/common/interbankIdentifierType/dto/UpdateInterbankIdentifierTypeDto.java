package com.moneda.bankAccount_microservice.common.interbankIdentifierType.dto;

import lombok.Data;

@Data
public class UpdateInterbankIdentifierTypeDto {
    private String code;
    private String countryCode;
    private String name;
}
