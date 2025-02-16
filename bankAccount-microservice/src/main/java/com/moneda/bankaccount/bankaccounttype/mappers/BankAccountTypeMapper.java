package com.moneda.bankaccount.bankaccounttype.mappers;

import com.moneda.bankaccount.bankaccounttype.dto.BankAccountTypeDto;
import com.moneda.bankaccount.bankaccounttype.entities.BankAccountType;
import com.moneda.bankaccount.currency.mappers.CurrencyMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BankAccountTypeMapper {
    private final CurrencyMapper currencyMapper;
    public BankAccountTypeDto toBankAccountTypeDto(BankAccountType bankAccountType) {
        BankAccountTypeDto dto = new BankAccountTypeDto();
        dto.setId(bankAccountType.getId());
        dto.setName(bankAccountType.getName());
        dto.setCode(bankAccountType.getCode());
        dto.setCountryCode(bankAccountType.getCountryCode());
        dto.setCurrency(currencyMapper.toCurrencyDto(bankAccountType.getCurrency()));
        return dto;
    }
}
