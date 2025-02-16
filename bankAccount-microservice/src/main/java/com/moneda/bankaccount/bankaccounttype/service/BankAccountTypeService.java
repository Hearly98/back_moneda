package com.moneda.bankaccount.bankaccounttype.service;

import com.moneda.bankaccount.bankaccounttype.dto.CreateBankAccountTypeDto;
import com.moneda.bankaccount.bankaccounttype.dto.UpdateBankAccountTypeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.UUID;

public interface BankAccountTypeService {
    ResponseEntity<Map<String, Object>> listBankAccountTypes();
    ResponseEntity<Map<String, Object>> saveBankAccountType(CreateBankAccountTypeDto createBankAccountTypeDto, BindingResult result);
    ResponseEntity<Map<String, Object>> updateBankAccountType(UUID id, UpdateBankAccountTypeDto updateBankAccountTypeDto, BindingResult result);
    ResponseEntity<Map<String, Object>> getBankAccountTypeById(UUID id);
    ResponseEntity<Map<String, Object>> deleteBankAccountType(UUID id);
}
