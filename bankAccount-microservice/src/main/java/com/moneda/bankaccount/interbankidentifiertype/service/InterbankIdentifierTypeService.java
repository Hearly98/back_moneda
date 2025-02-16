package com.moneda.bankaccount.common.interbankIdentifierType.service;

import com.moneda.bankaccount.common.interbankIdentifierType.dto.CreateInterbankIdentifierTypeDto;
import com.moneda.bankaccount.common.interbankIdentifierType.dto.UpdateInterbankIdentifierTypeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import java.util.Map;
import java.util.UUID;

public interface InterbankIdentifierTypeService {
    public ResponseEntity<Map<String, Object>> getAllInterbankIdentifierTypes();
    public ResponseEntity<Map<String, Object>> createInterbankIdentifierType(CreateInterbankIdentifierTypeDto identifier, BindingResult result);
    public ResponseEntity<Map<String, Object>> updateInterbankIdentifierType(UUID id, UpdateInterbankIdentifierTypeDto identifier, BindingResult result);
    public ResponseEntity<Map<String, Object>> getInterbankIdentifierTypeById(UUID id);
    public ResponseEntity<Map<String, Object>> deleteInterbankIdentifierType(UUID id);
}
