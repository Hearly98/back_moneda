package com.moneda.bankAccount_microservice.common.bankAccountType.mappers;

import com.moneda.bankAccount_microservice.common.bankAccountType.dto.BankAccountTypeDto;
import com.moneda.bankAccount_microservice.common.bankAccountType.entities.BankAccountType;
import com.moneda.bankAccount_microservice.common.currency.mappers.CurrencyMapper;
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
