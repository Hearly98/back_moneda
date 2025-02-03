package com.moneda.bankAccount_microservice.common.bankAccount.service;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;

public interface BankAccountService{
    public ResponseEntity<Map<String, Object>> listBankAccounts();
    public ResponseEntity<Map<String, Object>> saveBankAccount(Object createBankAccount, BindingResult result);
    public ResponseEntity<Map<String, Object>> updateBankAccount(Integer id, Object updateBankAccount, BindingResult result);
    public ResponseEntity<Map<String, Object>> getBankAccountById(Integer id);
    public ResponseEntity<Map<String, Object>> getBankAccountByCode(String code);
    public ResponseEntity<Map<String, Object>> deleteBankAccount(Integer id);
}
