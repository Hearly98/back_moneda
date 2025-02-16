package com.moneda.bankaccount.bankaccount.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeneratedBankAccountDto {
    private String accountNumber;
    private String interbankNumber;
    private String interbankIdentifierType;
    private String countryCode;
}
