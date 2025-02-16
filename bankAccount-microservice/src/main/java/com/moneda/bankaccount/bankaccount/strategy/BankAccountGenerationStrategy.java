package com.moneda.bankaccount.bankaccount.strategy;

public interface BankAccountGenerationStrategy {
    String generateBankAccount();
    String generateInterbankIdentifier(String accountNumber);
    String getInterbankIdentifierType();
}
