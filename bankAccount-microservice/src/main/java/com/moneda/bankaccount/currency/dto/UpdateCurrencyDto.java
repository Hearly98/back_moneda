package com.moneda.bankaccount.currency.dto;

import lombok.Data;

@Data
public class UpdateCurrencyDto {
    private String name;
    private String code;
    private String symbol;
}
