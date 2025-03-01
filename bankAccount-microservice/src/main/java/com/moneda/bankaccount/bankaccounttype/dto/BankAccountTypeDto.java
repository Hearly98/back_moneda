package com.moneda.bankaccount.bankaccounttype.dto;

import com.moneda.bankaccount.currency.dto.CurrencyDto;
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
