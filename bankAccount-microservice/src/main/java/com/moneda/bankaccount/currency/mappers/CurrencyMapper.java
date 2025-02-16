package com.moneda.bankaccount.common.currency.mappers;

import com.moneda.bankaccount.common.currency.dto.CurrencyDto;
import com.moneda.bankaccount.common.currency.entities.Currency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMapper {
    public CurrencyDto toCurrencyDto(Currency currency) {
        CurrencyDto dto = new CurrencyDto();
        dto.setId(currency.getId());
        dto.setName(currency.getName());
        dto.setCode(currency.getCode());
        dto.setSymbol(currency.getSymbol());
        return dto;
    }
}
