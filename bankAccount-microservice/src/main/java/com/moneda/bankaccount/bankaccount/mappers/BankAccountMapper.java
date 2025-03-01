package com.moneda.bankaccount.bankaccount.mappers;
import com.moneda.bankaccount.bankaccount.dto.BankAccountDto;
import com.moneda.bankaccount.bankaccount.entities.BankAccount;
import com.moneda.bankaccount.bankaccounttype.mappers.BankAccountTypeMapper;
import com.moneda.bankaccount.interbankidentifiertype.mappers.InterbankIdentifierTypeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BankAccountMapper {
    private final BankAccountTypeMapper bankAccountTypeMapper;
    private final InterbankIdentifierTypeMapper identifierTypeMapper;
    public BankAccountDto toBankAccountDto(BankAccount bankAccount) {
        BankAccountDto dto = new BankAccountDto();
        dto.setId(bankAccount.getId());
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
