package com.moneda.bankaccount.common.bankAccount.dto;

import com.moneda.bankaccount.common.bankAccountType.dto.BankAccountTypeDto;
import com.moneda.bankaccount.common.interbankIdentifierType.dto.InterbankIdentifierTypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto {
    private UUID interbankIdentifierId;
    private UUID bankAccountTypeId;
    private UUID userId;
    private String accountNumber;
    private String interbankNumber;
    private String alias;
    private BigDecimal balance;
    private InterbankIdentifierTypeDto interbankIdentifierType;
    private BankAccountTypeDto bankAccountType;
}
