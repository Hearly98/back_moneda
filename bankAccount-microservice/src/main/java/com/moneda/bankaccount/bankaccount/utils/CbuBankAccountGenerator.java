package com.moneda.bankaccount.bankaccount.utils;

import com.moneda.bankaccount.bankaccount.strategy.BankAccountGenerationStrategy;
import org.springframework.stereotype.Component;
import java.util.concurrent.ThreadLocalRandom;

@Component("cbuComponent")
public class CbuBankAccountGenerator implements BankAccountGenerationStrategy {

    private static final String BANK_CODE = "017";
    private static final String BRANCH_CODE = "1234";

    @Override
    public String generateInterbankIdentifier(String accountNumber) {
        if (accountNumber == null || accountNumber.length() != 10) {
            accountNumber = generateBankAccount();
        }
        String firstBlock = BANK_CODE + BRANCH_CODE;
        int firstVerifier = calculateModulo10(firstBlock);
        int secondVerifier = calculateModulo10(accountNumber);
        return firstBlock + firstVerifier + accountNumber + secondVerifier;
    }

    public String generateBankAccount() {
        long number = ThreadLocalRandom.current().nextLong(100_000_0000L, 1_000_000_0000L);
        return String.valueOf(number);
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

