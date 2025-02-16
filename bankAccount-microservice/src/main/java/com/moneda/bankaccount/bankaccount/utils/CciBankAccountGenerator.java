package com.moneda.bankaccount.common.bankAccount.utils;

import com.moneda.bankaccount.common.bankAccount.strategy.BankAccountGenerationStrategy;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("CCI")
public class CciBankAccountGenerator implements BankAccountGenerationStrategy {

    private static final String BANK_CODE = "002";
    private static final String BRANCH_CODE = "456";
    private static final Random RANDOM = new Random();
    @Override
    public String generateInterbankIdentifier(String accountNumber) {
        if (accountNumber == null || accountNumber.length() != 12) {
            accountNumber = generateBankAccount();
        }
        return BANK_CODE + BRANCH_CODE + accountNumber + calculateChecksum(BANK_CODE, BRANCH_CODE, accountNumber);
    }

    public String generateBankAccount() {
        long number = 1_000_000_000_000L + (long) (RANDOM.nextDouble() * 9_000_000_000_000L);
        return String.valueOf(number);
    }

    private static String calculateChecksum(String bankCode, String branchCode, String accountNumber) {
        long sum = (Long.parseLong(bankCode) + Long.parseLong(branchCode) + Long.parseLong(accountNumber)) % 97;
        return String.format("%02d", 98 - sum);
    }
    @Override
    public String getInterbankIdentifierType() {
        return "01";
    }
}
