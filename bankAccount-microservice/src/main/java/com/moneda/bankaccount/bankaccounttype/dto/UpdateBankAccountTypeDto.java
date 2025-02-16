package com.moneda.bankaccount.common.bankAccountType.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBankAccountTypeDto {
    private String name;
    private String code;
    private String countryCode;
    private UUID currency_id;
}
