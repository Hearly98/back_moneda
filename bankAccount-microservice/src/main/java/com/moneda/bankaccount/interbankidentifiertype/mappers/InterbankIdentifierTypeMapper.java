package com.moneda.bankaccount.interbankidentifiertype.mappers;

import com.moneda.bankaccount.interbankidentifiertype.dto.InterbankIdentifierTypeDto;
import com.moneda.bankaccount.interbankidentifiertype.entities.InterbankIdentifierType;
import org.springframework.stereotype.Component;

@Component
public class InterbankIdentifierTypeMapper {
    public InterbankIdentifierTypeDto toInterbankIdentifierTypeDto(InterbankIdentifierType interbankIdentifierType){
        InterbankIdentifierTypeDto dto = new InterbankIdentifierTypeDto();
        dto.setId(interbankIdentifierType.getId());
        dto.setCode(interbankIdentifierType.getCode());
        dto.setName(interbankIdentifierType.getName());
        dto.setCountryCode(interbankIdentifierType.getCountryCode());
        return dto;
    }
}
