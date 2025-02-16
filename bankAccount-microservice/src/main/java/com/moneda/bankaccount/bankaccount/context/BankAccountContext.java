package com.moneda.bankaccount.common.bankAccount.context;

import com.moneda.bankaccount.common.bankAccount.dto.GeneratedBankAccountDto;
import com.moneda.bankaccount.common.bankAccount.strategy.BankAccountGenerationStrategy;
import com.moneda.bankaccount.common.bankAccount.utils.NationalityCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class BankAccountContext {
    private final Map<String, BankAccountGenerationStrategy> strategies;

    @Autowired
    public BankAccountContext(Map<String, BankAccountGenerationStrategy> strategies) {
        this.strategies = strategies;
    }

    public GeneratedBankAccountDto generateBankAccount(String countryCode) {
        String operator = NationalityCode.fromCode(countryCode)
                .map(NationalityCode::getStrategyBeanName)
                .orElseThrow(() -> new RuntimeException("No hay un generador para este país"));

        BankAccountGenerationStrategy strategy = strategies.get(operator);

        // Generamos los datos de la cuenta bancaria
        String accountNumber = strategy.generateBankAccount();
        String interbankNumber = strategy.generateInterbankIdentifier(accountNumber);
        String interbankIdentifierType = strategy.getInterbankIdentifierType();

        return new GeneratedBankAccountDto(accountNumber, interbankNumber, interbankIdentifierType, countryCode);
    }
}

