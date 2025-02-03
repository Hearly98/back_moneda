package com.moneda.bankAccount_microservice.common.bankAccountType.dto;

import com.moneda.bankAccount_microservice.common.currency.dto.CurrencyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountTypeDto {
    private UUID id;
    private String name;
    private String code;
    private String countryCode;
    private CurrencyDto currency;
}
