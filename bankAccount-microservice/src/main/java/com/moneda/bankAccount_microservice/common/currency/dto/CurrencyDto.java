package com.moneda.bankAccount_microservice.common.currency.dto;
import lombok.Data;

import java.util.UUID;

@Data
public class CurrencyDto {
    private UUID id;
    private String name;
    private String code;
    private String symbol;
}
