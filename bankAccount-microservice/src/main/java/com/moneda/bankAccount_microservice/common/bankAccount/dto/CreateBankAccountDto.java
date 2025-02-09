package com.moneda.bankAccount_microservice.common.bankAccount.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateBankAccountDto {
    private UUID userId;
    private String bankAccountTypeCode;
}
