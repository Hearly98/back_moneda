package com.moneda.bankAccount_microservice.common.bankAccount.dto;

import com.moneda.bankAccount_microservice.common.bankAccountType.dto.BankAccountTypeDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class UpdateBankAccountDto {
    private String accountNumber;
    private String interbankOperatorNumber;
    private BigDecimal balance;
    private String alias;
    private UUID userId;
    private BankAccountTypeDto bankAccountType;
}
