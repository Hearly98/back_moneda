package com.moneda.bankaccount.interbankidentifiertype.dto;

import lombok.Data;

@Data
public class UpdateInterbankIdentifierTypeDto {
    private String code;
    private String countryCode;
    private String name;
}
