package com.moneda.bankaccount.bankaccount.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateBankAccountDto {
    private UUID userId;
    private String bankAccountTypeCode;
    private String countryCode;
}
