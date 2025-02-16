package com.moneda.bankaccount.common.bankAccount.mappers;
import com.moneda.bankaccount.common.bankAccount.dto.BankAccountDto;
import com.moneda.bankaccount.common.bankAccount.entities.BankAccount;
import com.moneda.bankaccount.common.bankAccountType.mappers.BankAccountTypeMapper;
import com.moneda.bankaccount.common.interbankIdentifierType.mappers.InterbankIdentifierTypeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BankAccountMapper {
    private final BankAccountTypeMapper bankAccountTypeMapper;
    private final InterbankIdentifierTypeMapper identifierTypeMapper;
    public BankAccountDto toBankAccountDto(BankAccount bankAccount) {
        BankAccountDto dto = new BankAccountDto();
        dto.setAccountNumber(bankAccount.getAccountNumber());
        dto.setUserId(bankAccount.getUserId());
        dto.setInterbankIdentifierId(bankAccount.getInterbankIdentifierType().getId());
        dto.setBankAccountTypeId(bankAccount.getBankAccountType().getId());
        dto.setInterbankIdentifierType(identifierTypeMapper.toInterbankIdentifierTypeDto(bankAccount.getInterbankIdentifierType()));
        dto.setInterbankNumber(bankAccount.getInterbankNumber());
        dto.setBankAccountType(bankAccountTypeMapper.toBankAccountTypeDto(bankAccount.getBankAccountType()));
        dto.setBalance(bankAccount.getBalance());
        dto.setAlias(bankAccount.getAlias());
        return dto;
    }
}
