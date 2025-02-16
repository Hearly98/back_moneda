package com.moneda.bankaccount.common.currency.service;

import com.moneda.bankaccount.common.currency.dto.CreateCurrencyDto;
import com.moneda.bankaccount.common.currency.dto.UpdateCurrencyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.UUID;

public interface CurrencyService {
    public ResponseEntity<Map<String, Object>> getCurrencies();
    public ResponseEntity<Map<String, Object>> saveCurrency(CreateCurrencyDto currency, BindingResult result);
    public ResponseEntity<Map<String, Object>> updateCurrency(UUID id, UpdateCurrencyDto currency, BindingResult result);
    public ResponseEntity<Map<String, Object>> getCurrencyById(UUID id);
    public ResponseEntity<Map<String, Object>> deleteCurrency(UUID id);
}
