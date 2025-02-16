package com.moneda.bankaccount.bankaccount.service;
import com.moneda.bankaccount.bankaccount.dto.CreateBankAccountDto;
import com.moneda.bankaccount.bankaccount.dto.UpdateBankAccountDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.UUID;

public interface BankAccountService{
    ResponseEntity<Map<String, Object>> listBankAccounts();
    ResponseEntity<Map<String, Object>> saveBankAccount(CreateBankAccountDto createBankAccount, BindingResult result);
    ResponseEntity<Map<String, Object>> updateBankAccount(UUID id, UpdateBankAccountDto updateBankAccount, BindingResult result);
    ResponseEntity<Map<String, Object>> getBankAccountById(UUID id);
    ResponseEntity<Map<String, Object>> getBankAccountByInterbankNumber(String interbankNumber);
    ResponseEntity<Map<String, Object>> deleteBankAccount(UUID id);
}
