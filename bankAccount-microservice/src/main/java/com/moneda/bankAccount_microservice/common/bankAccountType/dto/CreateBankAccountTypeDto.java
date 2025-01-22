package com.moneda.bankAccount_microservice.common.bankAccountType.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateBankAccountTypeDto {
    private String name;
    private String code;
    private UUID currency_id;
}
