package com.moneda.bankaccount.bankaccount.dto;

import com.moneda.bankaccount.bankaccounttype.dto.BankAccountTypeDto;
import com.moneda.bankaccount.interbankidentifiertype.dto.InterbankIdentifierTypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto {
    private UUID id;
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
