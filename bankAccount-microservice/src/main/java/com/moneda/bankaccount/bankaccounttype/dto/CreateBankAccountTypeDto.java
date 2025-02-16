package com.moneda.bankaccount.common.bankAccountType.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateBankAccountTypeDto {
    private String name;
    private String code;
    private String countryCode;
    private UUID currency_id;
}
