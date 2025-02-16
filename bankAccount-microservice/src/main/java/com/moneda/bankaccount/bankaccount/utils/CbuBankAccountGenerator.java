package com.moneda.bankaccount.common.bankAccount.utils;

import com.moneda.bankaccount.common.bankAccount.strategy.BankAccountGenerationStrategy;
import org.springframework.stereotype.Component;

@Component("CBU")
public class CbuBankAccountGenerator implements BankAccountGenerationStrategy {

    private static final String BANK_CODE = "017";
    private static final String BRANCH_CODE = "1234";

    @Override
    public String generateInterbankIdentifier(String accountNumber){
        String firstBlock = BANK_CODE + BRANCH_CODE;
        int firstVerifier = calculateModulo10(firstBlock);
        int secondVerifier = calculateModulo10(accountNumber);
        return firstBlock + firstVerifier + accountNumber + secondVerifier;
    }

    public String generateBankAccount() {
        return String.format("%010d", (long) (Math.random() * 1_000_000_0000L));
    }

    private static int calculateModulo10(String input) {
        int[] weights = {7, 1, 3};
        int sum = 0;
        for (int i = 0; i < input.length(); i++) {
            sum += Character.getNumericValue(input.charAt(i)) * weights[i % weights.length];
        }
        return (10 - (sum % 10)) % 10;
    }
    @Override
    public String getInterbankIdentifierType() {
        return "02";
    }
}

