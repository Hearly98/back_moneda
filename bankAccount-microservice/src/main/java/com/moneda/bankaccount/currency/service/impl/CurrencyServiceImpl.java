package com.moneda.bankaccount.currency.service.impl;

import com.moneda.bankaccount.currency.dto.CreateCurrencyDto;
import com.moneda.bankaccount.currency.dto.CurrencyDto;
import com.moneda.bankaccount.currency.dto.UpdateCurrencyDto;
import com.moneda.bankaccount.currency.mappers.CurrencyMapper;
import com.moneda.bankaccount.currency.repositories.CurrencyRepository;
import com.moneda.bankaccount.currency.service.CurrencyService;
import com.moneda.bankaccount.currency.entities.Currency;
import com.moneda.utils.HttpStatusResponse;
import com.moneda.utils.ResponseEntityCustom;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

@Service
@AllArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;
    @Override
    public ResponseEntity<Map<String, Object>> getCurrencies() {
        List<CurrencyDto> currencies = currencyRepository.findByIsActiveTrue()
                .stream()
                .map(currencyMapper::toCurrencyDto)
                .toList();
        return currencies.isEmpty() ?
                ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(), Collections.emptyList(),
                        HttpStatusResponse.NOT_FOUND.getCode()) :
                ResponseEntityCustom.builderResponse(HttpStatusResponse.OK.getKey(), currencies,
                        HttpStatusResponse.OK.getCode());
    }

    @Override
    public ResponseEntity<Map<String, Object>> saveCurrency(CreateCurrencyDto createCurrencyDto, BindingResult result) {
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.BAD_REQUEST.getKey(), errors,
                    HttpStatusResponse.BAD_REQUEST.getCode());
        }
        try{
            Currency currency = new Currency();
            currency.setName(createCurrencyDto.getName());
            currency.setCode(createCurrencyDto.getCode());
            currency.setSymbol(createCurrencyDto.getSymbol());
            currency.setCreatedAt(new Date());
            currency.setIsActive(true); //por defecto estará activo al crear una moneda
            currencyRepository.save(currency);

            CurrencyDto currencyDto = currencyMapper.toCurrencyDto(currency);
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.CREATED.getKey(), currencyDto,
                    HttpStatusResponse.CREATED.getCode());
        }catch (Exception e){
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.BAD_REQUEST.getKey(),
                    Collections.singletonList(e.getMessage()), HttpStatusResponse.BAD_REQUEST.getCode());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateCurrency(UUID id, UpdateCurrencyDto currencyDto,
                                                              BindingResult result) {
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.BAD_REQUEST.getKey(), errors,
                    HttpStatusResponse.BAD_REQUEST.getCode());
        }

        Optional<Currency> existingCurrency = currencyRepository.findById(id);
        if (existingCurrency.isEmpty()) {
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(), null,
                    HttpStatusResponse.NOT_FOUND.getCode());
        }
        try {
            Currency currencyEntity = existingCurrency.get();
            currencyEntity.setName(currencyDto.getName());
            currencyEntity.setCode(currencyDto.getCode());
            currencyEntity.setSymbol(currencyDto.getSymbol());
            currencyEntity.setLastModified(new Date());
            Currency updatedCurrency = currencyRepository.save(currencyEntity);

            CurrencyDto dto = currencyMapper.toCurrencyDto(updatedCurrency);
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.UPDATED.getKey(), dto,
                    HttpStatusResponse.UPDATED.getCode());
        } catch (Exception e) {
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(),
                    Collections.singletonList(e.getMessage()), HttpStatusResponse.INTERNAL_ERROR.getCode());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getCurrencyById(UUID id) {
        try {
            Optional<Currency> currencyOptional = currencyRepository.findById(id);
            if (currencyOptional.isEmpty()) {
                return ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(),
                        Collections.emptyList(), HttpStatusResponse.NOT_FOUND.getCode());
            }
            CurrencyDto currencyDto = currencyMapper.toCurrencyDto(currencyOptional.get());
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.OK.getKey(),
                    currencyDto, HttpStatusResponse.OK.getCode());

        } catch (Exception e) {
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(),
                    Collections.singletonList(e.getMessage()), HttpStatusResponse.INTERNAL_ERROR.getCode());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteCurrency(UUID id) {
        try{
            Optional<Currency> existingCurrency = currencyRepository.findById(id);
            if (existingCurrency.isEmpty()) {
                return ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(),
                        Collections.emptyList(), HttpStatusResponse.NOT_FOUND.getCode());
            }
            Currency currencyEntity = existingCurrency.get();
            currencyEntity.setIsActive(false);
            currencyRepository.save(currencyEntity);
            CurrencyDto currencyDto = currencyMapper.toCurrencyDto(currencyEntity);
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.DELETED.getKey(),
                    currencyDto, HttpStatusResponse.DELETED.getCode());
        }
        catch (Exception e){
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(),
                    Collections.singletonList(e.getMessage()), HttpStatusResponse.INTERNAL_ERROR.getCode());
        }
    }
}
