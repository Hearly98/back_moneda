package com.moneda.user_microservice.documenttype.service;

import com.moneda.user_microservice.documenttype.dto.CreateDocumentTypeDto;
import com.moneda.user_microservice.documenttype.dto.UpdateDocumentTypeDto;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.UUID;

public interface DocumentTypeService {

    ResponseEntity<Map<String, Object>> listDocumentType();
    ResponseEntity<Map<String, Object>> saveDocumentType(CreateDocumentTypeDto createDocumentTypeDto, BindingResult bindingResult);
    ResponseEntity<Map<String, Object>> updateDocumentType(UUID id, UpdateDocumentTypeDto updateDocumentTypeDto, BindingResult bindingResult);
    ResponseEntity<Map<String, Object>> getDocumentTypeById(UUID id);
    ResponseEntity<Map<String, Object>> deleteDocumentType(UUID id);

}
