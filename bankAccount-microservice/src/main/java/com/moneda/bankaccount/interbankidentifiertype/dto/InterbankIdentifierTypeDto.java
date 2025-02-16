package com.moneda.bankaccount.interbankidentifiertype.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterbankIdentifierTypeDto {
    private UUID id;
    private String code;
    private String countryCode;
    private String name;
}
