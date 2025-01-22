package com.moneda.bankAccount_microservice.common.currency.dto;

import lombok.Data;

@Data
public class CreateCurrencyDto {
    private String name;
    private String code;
    private String symbol;
}
