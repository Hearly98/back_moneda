package com.moneda.bankaccount.common.bankAccount.dto;

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
