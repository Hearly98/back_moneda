package com.moneda.bankAccount_microservice.common.bankAccount.dto;

import com.moneda.bankAccount_microservice.common.bankAccountType.dto.BankAccountTypeDto;
import com.moneda.bankAccount_microservice.common.interbankIdentifierType.entities.InterbankIdentifierType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto {
    private String accountNumber;
    private InterbankIdentifierType interbankIdentifierType;
    private String interbankNumber;
    private BigDecimal balance;
    private String alias;
    private UUID userId;
    private BankAccountTypeDto bankAccountType;
}
