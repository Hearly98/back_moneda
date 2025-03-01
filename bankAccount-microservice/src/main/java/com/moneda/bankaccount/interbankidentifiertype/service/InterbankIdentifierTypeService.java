package com.moneda.bankaccount.interbankidentifiertype.service;

import com.moneda.bankaccount.interbankidentifiertype.dto.CreateInterbankIdentifierTypeDto;
import com.moneda.bankaccount.interbankidentifiertype.dto.UpdateInterbankIdentifierTypeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import java.util.Map;
import java.util.UUID;

public interface InterbankIdentifierTypeService {
    ResponseEntity<Map<String, Object>> getAllInterbankIdentifierTypes();
    ResponseEntity<Map<String, Object>> createInterbankIdentifierType(CreateInterbankIdentifierTypeDto identifier, BindingResult result);
    ResponseEntity<Map<String, Object>> updateInterbankIdentifierType(UUID id, UpdateInterbankIdentifierTypeDto identifier, BindingResult result);
    ResponseEntity<Map<String, Object>> getInterbankIdentifierTypeById(UUID id);
    ResponseEntity<Map<String, Object>> deleteInterbankIdentifierType(UUID id);
}
