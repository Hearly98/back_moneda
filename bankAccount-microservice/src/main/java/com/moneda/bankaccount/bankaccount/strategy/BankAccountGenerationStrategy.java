package com.moneda.bankaccount.common.bankAccount.strategy;

public interface BankAccountGenerationStrategy {
    String generateBankAccount();
    String generateInterbankIdentifier(String accountNumber);
    String getInterbankIdentifierType();
}
