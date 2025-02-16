package com.moneda.bankaccount.common.bankAccount.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class UpdateBankAccountDto {
    private String accountNumber;
    private String interbankOperatorCode;
    private String interbankNumber;
    private BigDecimal balance;
    private String alias;
    private UUID userId;
    private String bankAccountTypeCode;
}
