package com.moneda.bankAccount_microservice.common.bankAccount.service;
import com.moneda.bankAccount_microservice.common.bankAccount.dto.CreateBankAccountDto;
import com.moneda.bankAccount_microservice.common.bankAccount.dto.UpdateBankAccountDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.UUID;

public interface BankAccountService{
    public ResponseEntity<Map<String, Object>> listBankAccounts();
    public ResponseEntity<Map<String, Object>> saveBankAccount(CreateBankAccountDto createBankAccount, BindingResult result);
    public ResponseEntity<Map<String, Object>> updateBankAccount(UUID id, UpdateBankAccountDto updateBankAccount, BindingResult result);
    public ResponseEntity<Map<String, Object>> getBankAccountById(UUID id);
    public ResponseEntity<Map<String, Object>> getBankAccountByInterbankNumber(String interbankNumber);
    public ResponseEntity<Map<String, Object>> deleteBankAccount(UUID id);
}
