package com.moneda.bankaccount.currency.service;

import com.moneda.bankaccount.currency.dto.CreateCurrencyDto;
import com.moneda.bankaccount.currency.dto.UpdateCurrencyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.UUID;

public interface CurrencyService {
    ResponseEntity<Map<String, Object>> getCurrencies();
    ResponseEntity<Map<String, Object>> saveCurrency(CreateCurrencyDto currency, BindingResult result);
    ResponseEntity<Map<String, Object>> updateCurrency(UUID id, UpdateCurrencyDto currency, BindingResult result);
    ResponseEntity<Map<String, Object>> getCurrencyById(UUID id);
    ResponseEntity<Map<String, Object>> deleteCurrency(UUID id);
}
