package com.moneda.bankaccount.bankaccounttype.service.impl;

import com.moneda.bankaccount.bankaccounttype.dto.BankAccountTypeDto;
import com.moneda.bankaccount.bankaccounttype.dto.CreateBankAccountTypeDto;
import com.moneda.bankaccount.bankaccounttype.dto.UpdateBankAccountTypeDto;
import com.moneda.bankaccount.bankaccounttype.entities.BankAccountType;
import com.moneda.bankaccount.currency.entities.Currency;
import com.moneda.bankaccount.bankaccounttype.mappers.BankAccountTypeMapper;
import com.moneda.bankaccount.bankaccounttype.repositories.BankAccountTypeRepository;
import com.moneda.bankaccount.bankaccounttype.service.BankAccountTypeService;
import com.moneda.bankaccount.currency.repositories.CurrencyRepository;
import com.moneda.utils.HttpStatusResponse;
import com.moneda.utils.ResponseEntityCustom;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.*;

@Service
@AllArgsConstructor
public class BankAccountTypeServiceImpl implements BankAccountTypeService {
    private final BankAccountTypeRepository bankAccountTypeRepository;
    private final BankAccountTypeMapper bankAccountTypeMapper;
    private final CurrencyRepository currencyRepository;
    @Override
    public ResponseEntity<Map<String, Object>> listBankAccountTypes() {
        List<BankAccountTypeDto> bankAccountTypes = bankAccountTypeRepository.findByIsActiveTrue()
                .stream()
                .map(bankAccountTypeMapper::toBankAccountTypeDto)
                .toList();
        return bankAccountTypes.isEmpty() ?
                ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(),
                        Collections.emptyList(), HttpStatusResponse.NOT_FOUND.getCode()) :
                ResponseEntityCustom.builderResponse(HttpStatusResponse.OK.getKey(),
                        bankAccountTypes, HttpStatusResponse.OK.getCode());
    }

    @Override
    public ResponseEntity<Map<String, Object>> saveBankAccountType(CreateBankAccountTypeDto createBankAccountTypeDto,
                                                                   BindingResult result) {
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.BAD_REQUEST.getKey(), errors,
                    HttpStatusResponse.BAD_REQUEST.getCode());
        }
        try{
            BankAccountType bankAccountType = new BankAccountType();
            bankAccountType.setName(createBankAccountTypeDto.getName());
            bankAccountType.setCode(createBankAccountTypeDto.getCode());
            Currency currency = currencyRepository.findById(createBankAccountTypeDto.getCurrency_id())
                    .orElseThrow(() -> new RuntimeException("Moneda no encontrada"));
            bankAccountType.setCurrency(currency);
            bankAccountType.setCountryCode(createBankAccountTypeDto.getCountryCode());
            bankAccountType.setCreatedAt(new Date()); //colocará la fecha actual
            bankAccountType.setIsActive(true); //por defecto estará activo al crear
            bankAccountTypeRepository.save(bankAccountType);

            BankAccountTypeDto bankAccountTypeDto = bankAccountTypeMapper.toBankAccountTypeDto(bankAccountType);
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.CREATED.getKey(), bankAccountTypeDto,
                    HttpStatusResponse.CREATED.getCode());
        }catch (Exception e){
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(),
                    Collections.singletonList(e.getMessage()), HttpStatusResponse.INTERNAL_ERROR.getCode());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateBankAccountType(UUID id, UpdateBankAccountTypeDto updateBankAccountTypeDto, BindingResult result) {
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.BAD_REQUEST.getKey(), errors,
                    HttpStatusResponse.BAD_REQUEST.getCode());
        }

        Optional<BankAccountType> existingBankAccountType = bankAccountTypeRepository.findById(id);
        if (existingBankAccountType.isEmpty()) {
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(),
                    Collections.emptyList(), HttpStatusResponse.NOT_FOUND.getCode());
        }
        try {
            BankAccountType bankAccountType = existingBankAccountType.get();
            bankAccountType.setName(updateBankAccountTypeDto.getName());
            bankAccountType.setCode(updateBankAccountTypeDto.getCode());
            Currency currency = currencyRepository.findById(updateBankAccountTypeDto.getCurrency_id())
                    .orElseThrow(() -> new RuntimeException("Moneda no encontrada"));
            bankAccountType.setCurrency(currency);
            bankAccountType.setCountryCode(updateBankAccountTypeDto.getCountryCode());
            bankAccountType.setLastModified(new Date()); //colocará la fecha actual
            BankAccountType updateBankAccountType = bankAccountTypeRepository.save(bankAccountType);
            BankAccountTypeDto bankAccountTypeDto = bankAccountTypeMapper.toBankAccountTypeDto(updateBankAccountType);
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.UPDATED.getKey(),
                    bankAccountTypeDto, HttpStatusResponse.UPDATED.getCode());
        } catch (Exception e) {
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(),
                    Collections.singletonList(e.getMessage()), HttpStatusResponse.INTERNAL_ERROR.getCode());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getBankAccountTypeById(UUID id) {
        try {
            Optional<BankAccountType> bankAccountTypeOptional = bankAccountTypeRepository.findById(id);
            if (bankAccountTypeOptional.isEmpty()) {
                return ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(),
                        Collections.emptyList(), HttpStatusResponse.NOT_FOUND.getCode());
            }
            BankAccountTypeDto bankAccountTypeDto = bankAccountTypeMapper
                    .toBankAccountTypeDto(bankAccountTypeOptional.get());
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.OK.getKey(), bankAccountTypeDto,
                    HttpStatusResponse.OK.getCode());

        } catch (Exception e) {
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(), e.getMessage(),
                    HttpStatusResponse.INTERNAL_ERROR.getCode());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteBankAccountType(UUID id) {
        try{
            Optional<BankAccountType> existingBankAccountType = bankAccountTypeRepository.findById(id);
            if (existingBankAccountType.isEmpty()) {
                return ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(),
                        Collections.emptyList(), HttpStatusResponse.NOT_FOUND.getCode());
            }
            BankAccountType bankAccountTypeEntity = existingBankAccountType.get();
            bankAccountTypeEntity.setIsActive(false);
            bankAccountTypeRepository.save(bankAccountTypeEntity);
            BankAccountTypeDto bankAccountTypeDto = bankAccountTypeMapper.toBankAccountTypeDto(existingBankAccountType.get());
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.DELETED.getKey(), bankAccountTypeDto, HttpStatusResponse.DELETED.getCode());
        }
        catch (Exception e){
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(),
                    Collections.singletonList(e.getMessage()), HttpStatusResponse.INTERNAL_ERROR.getCode());
        }
    }
}
