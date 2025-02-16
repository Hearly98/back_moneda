package com.moneda.bankaccount.common.bankAccountType.service;

import com.moneda.bankaccount.common.bankAccountType.dto.CreateBankAccountTypeDto;
import com.moneda.bankaccount.common.bankAccountType.dto.UpdateBankAccountTypeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.UUID;

public interface BankAccountTypeService {
    public ResponseEntity<Map<String, Object>> listBankAccountTypes();
    public ResponseEntity<Map<String, Object>> saveBankAccountType(CreateBankAccountTypeDto createBankAccountTypeDto, BindingResult result);
    public ResponseEntity<Map<String, Object>> updateBankAccountType(UUID id, UpdateBankAccountTypeDto updateBankAccountTypeDto, BindingResult result);
    public ResponseEntity<Map<String, Object>> getBankAccountTypeById(UUID id);
    public ResponseEntity<Map<String, Object>> deleteBankAccountType(UUID id);
}
