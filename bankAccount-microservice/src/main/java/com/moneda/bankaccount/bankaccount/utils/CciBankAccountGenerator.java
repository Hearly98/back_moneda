package com.moneda.bankaccount.bankaccount.utils;

import com.moneda.bankaccount.bankaccount.strategy.BankAccountGenerationStrategy;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

@Component("cciComponent")
public class CciBankAccountGenerator implements BankAccountGenerationStrategy {

    private static final String BANK_CODE = "002";
    private static final String BRANCH_CODE = "456";

    @Override
    public String generateInterbankIdentifier(String accountNumber) {
        if (accountNumber == null || accountNumber.length() != 12) {
            accountNumber = generateBankAccount();
        }
        return BANK_CODE + BRANCH_CODE + accountNumber + calculateChecksum(accountNumber);
    }

    public String generateBankAccount() {
        long number = ThreadLocalRandom.current().nextLong(1_000_000_000_000L, 10_000_000_000_000L);
        return String.valueOf(number).substring(1, 13); // Asegura 12 dígitos
    }

    private static String calculateChecksum(String accountNumber) {
        try {
            BigInteger sum = new BigInteger(BANK_CODE).add(new BigInteger(BRANCH_CODE)).add(new BigInteger(accountNumber));
            BigInteger mod = sum.mod(BigInteger.valueOf(97));
            return String.format("%02d", 98 - mod.intValue());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid account number: " + accountNumber, e);
        }
    }

    @Override
    public String getInterbankIdentifierType() {
        return "01";
    }
}
