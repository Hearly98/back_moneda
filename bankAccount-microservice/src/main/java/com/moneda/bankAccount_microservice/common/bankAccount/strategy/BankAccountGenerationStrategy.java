package com.moneda.bankAccount_microservice.common.bankAccount.strategy;

public interface BankAccountGenerationStrategy {
    String generateBankAccount();
    String generateInterbankIdentifier(String accountNumber);
    String getInterbankIdentifierType();
}
