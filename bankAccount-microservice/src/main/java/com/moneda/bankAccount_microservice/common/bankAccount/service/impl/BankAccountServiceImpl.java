package com.moneda.bankAccount_microservice.common.bankAccount.service.impl;

import com.moneda.bankAccount_microservice.common.bankAccount.dto.BankAccountDto;
import com.moneda.bankAccount_microservice.common.bankAccount.dto.CreateBankAccountDto;
import com.moneda.bankAccount_microservice.common.bankAccount.dto.UpdateBankAccountDto;
import com.moneda.bankAccount_microservice.common.bankAccount.entities.BankAccount;
import com.moneda.bankAccount_microservice.common.bankAccount.mappers.BankAccountMapper;
import com.moneda.bankAccount_microservice.common.bankAccount.repositories.BankAccountRepository;
import com.moneda.bankAccount_microservice.common.bankAccount.service.BankAccountService;
import com.moneda.bankAccount_microservice.common.bankAccountType.entities.BankAccountType;
import com.moneda.bankAccount_microservice.common.bankAccountType.repositories.BankAccountTypeRepository;
import com.moneda.bankAccount_microservice.common.interbankIdentifierType.entities.InterbankIdentifierType;
import com.moneda.bankAccount_microservice.common.interbankIdentifierType.repositories.InterbankIdentifierTypeRepository;
import com.moneda.bankAccount_microservice.common.utils.BankAccountGenerator;
import com.moneda.utils.HttpStatusResponse;
import com.moneda.utils.ResponseEntityCustom;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final BankAccountTypeRepository bankAccountTypeRepository;
    private final BankAccountMapper bankAccountMapper;
    private final InterbankIdentifierTypeRepository interbankIdentifierTypeRepository;
    @Override
    public ResponseEntity<Map<String, Object>> listBankAccounts() {
        List<BankAccountDto> identifiers = bankAccountRepository.findByIsActiveTrue()
                .stream()
                .map(bankAccountMapper::toBankAccountDto)
                .toList();
        return identifiers.isEmpty() ?
                ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(), Collections.emptyList(),
                        HttpStatusResponse.NOT_FOUND.getCode()) :
                ResponseEntityCustom.builderResponse(HttpStatusResponse.OK.getKey(), identifiers,
                        HttpStatusResponse.OK.getCode());
    }

    @Override
    @Transactional
    public ResponseEntity<Map<String, Object>> saveBankAccount(CreateBankAccountDto createBankAccount,
                                                               BindingResult result) {
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.BAD_REQUEST.getKey(), errors,
                    HttpStatusResponse.BAD_REQUEST.getCode());
        }
        try {
            BankAccountType bankAccountType = bankAccountTypeRepository.findByCode(createBankAccount
                    .getBankAccountTypeCode()).orElseThrow(()-> new RuntimeException("Tipo Cuenta Banco no encontrado"));
            String accountNumber = BankAccountGenerator.generateAccountNumber();
            String interbankNumber = BankAccountGenerator.generateCbu(accountNumber, "123", "4567");
            String alias = BankAccountGenerator.generateAlias();
            BankAccount bankAccount = new BankAccount();
            bankAccount.setAccountNumber(accountNumber);
            if (bankAccountRepository.existsByAccountNumber(accountNumber)) {
               return ResponseEntityCustom.builderResponse(HttpStatusResponse.BAD_REQUEST.getKey(),
                       Collections.emptyList(), HttpStatusResponse.BAD_REQUEST.getCode());
            }
            bankAccount.setInterbankNumber(interbankNumber);
            bankAccount.setBalance(BigDecimal.ZERO);
            bankAccount.setAlias(alias);
            if (bankAccountRepository.existsByAlias(alias)) {
                return ResponseEntityCustom.builderResponse(HttpStatusResponse.BAD_REQUEST.getKey(), null,
                        HttpStatusResponse.BAD_REQUEST.getCode());
            }
            bankAccount.setBankAccountType(bankAccountType);
            bankAccount.setUserId(createBankAccount.getUserId());
            bankAccount.setIsActive(true);
            bankAccount.setCreatedAt(new Date());
            bankAccountRepository.save(bankAccount);
            BankAccountDto bankAccountDto = bankAccountMapper.toBankAccountDto(bankAccount);
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.CREATED.getKey(), bankAccountDto,
                    HttpStatusResponse.CREATED.getCode());
        } catch (Exception e) {
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.BAD_REQUEST.getKey(), null,
                    HttpStatusResponse.BAD_REQUEST.getCode());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Map<String, Object>> updateBankAccount(UUID id, UpdateBankAccountDto updateBankAccount,
                                                                 BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();
            response.put("message", "Error en la validación");
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }

        Optional<BankAccount> existingBankAccount = bankAccountRepository.findById(id);
        if (existingBankAccount.isEmpty()) {
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(), null,
                    HttpStatusResponse.NOT_FOUND.getCode());
        }
        try {
            BankAccountType bankAccountType = bankAccountTypeRepository.
                    findByCode(updateBankAccount.getBankAccountTypeCode())
                    .orElseThrow(()-> new RuntimeException("Tipo Cuenta Banco no encontrado"));
            InterbankIdentifierType interbankIdentifierType = interbankIdentifierTypeRepository
                    .findByCode(updateBankAccount.getBankAccountTypeCode())
                    .orElseThrow(()-> new RuntimeException("Operador interbancario no encontrado"));
            BankAccount bankAccount = existingBankAccount.get();
            bankAccount.setAccountNumber(updateBankAccount.getAccountNumber());
            bankAccount.setInterbankIdentifierType(interbankIdentifierType);
            bankAccount.setInterbankNumber(updateBankAccount.getInterbankNumber());
            bankAccount.setAlias(updateBankAccount.getAlias());
            bankAccount.setBalance(updateBankAccount.getBalance());
            bankAccount.setUserId(updateBankAccount.getUserId());
            bankAccount.setBankAccountType(bankAccountType);
            bankAccount.setLastModified(new Date()); //colocará la fecha actual
            bankAccountRepository.save(bankAccount);

            BankAccountDto bankAccountDto = bankAccountMapper.toBankAccountDto(bankAccount);
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.UPDATED.getKey(), bankAccountDto,
                    HttpStatusResponse.UPDATED.getCode());
        } catch (Exception e) {
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(), e.getMessage(),
                    HttpStatusResponse.INTERNAL_ERROR.getCode());
    }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getBankAccountById(UUID id) {
        try {
            Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(id);
            if (bankAccountOptional.isEmpty()) {
                return ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(), null,
                        HttpStatusResponse.NOT_FOUND.getCode());
            }
            BankAccountDto bankAccountDto = bankAccountMapper.toBankAccountDto(bankAccountOptional.get());
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.OK.getKey(), bankAccountDto,
                    HttpStatusResponse.OK.getCode());

        } catch (Exception e) {
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(), e.getMessage(),
                    HttpStatusResponse.INTERNAL_ERROR.getCode());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getBankAccountByInterbankNumber(String interbankNumber) {
        try {
            Optional<BankAccount> bankAccountOptional = bankAccountRepository.findByInterbankNumber(interbankNumber);
            if (bankAccountOptional.isEmpty()) {
                return ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(), null,
                        HttpStatusResponse.NOT_FOUND.getCode());
            }
            BankAccountDto bankAccountDto = bankAccountMapper.toBankAccountDto(bankAccountOptional.get());
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.OK.getKey(), bankAccountDto,
                    HttpStatusResponse.OK.getCode());

        } catch (Exception e) {
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(), e.getMessage(),
                    HttpStatusResponse.INTERNAL_ERROR.getCode());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteBankAccount(UUID id) {
        try{
            Optional<BankAccount> existingBankAccount = bankAccountRepository.findById(id);
            if (existingBankAccount.isEmpty()) {
                return ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(), null, HttpStatusResponse.NOT_FOUND.getCode());
            }
            BankAccount bankAccountEntity = existingBankAccount.get();
            bankAccountEntity.setIsActive(false);
            bankAccountRepository.save(bankAccountEntity);
            BankAccountDto bankAccountDto = bankAccountMapper.toBankAccountDto(existingBankAccount.get());
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.DELETED.getKey(), bankAccountDto,
                    HttpStatusResponse.DELETED.getCode());
        }
        catch (Exception e){
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(), e.getMessage(),
                    HttpStatusResponse.INTERNAL_ERROR.getCode());
        }
    }
}
