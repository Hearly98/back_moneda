package com.moneda.user_microservice.documenttype.service.impl;

import com.moneda.user_microservice.documenttype.dto.CreateDocumentTypeDto;
import com.moneda.user_microservice.documenttype.dto.DocumentTypeDto;
import com.moneda.user_microservice.documenttype.dto.UpdateDocumentTypeDto;
import com.moneda.user_microservice.documenttype.entity.DocumentType;
import com.moneda.user_microservice.documenttype.mappers.DocumentTypeMapper;
import com.moneda.user_microservice.documenttype.repository.DocumentTypeRepository;
import com.moneda.user_microservice.documenttype.service.DocumentTypeService;

import com.moneda.utils.HttpStatusResponse;
import com.moneda.utils.ResponseEntityCustom;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.*;

@Service
@AllArgsConstructor
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentTypeMapper documentTypeMapper;

    @Override
    public ResponseEntity<Map<String, Object>> listDocumentType() {
        List<DocumentTypeDto> documentType =documentTypeRepository.findByIsActiveTrue()
                .stream()
                .map(documentTypeMapper::toDocumentTypeDto)
                .toList();
        return documentType.isEmpty() ?
                ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(), Collections.emptyList(),
                        HttpStatusResponse.NOT_FOUND.getCode()) :
                ResponseEntityCustom.builderResponse(HttpStatusResponse.OK.getKey(), documentType,
                        HttpStatusResponse.OK.getCode());
    }

    @Transactional
    @Override
    public ResponseEntity<Map<String, Object>> saveDocumentType(CreateDocumentTypeDto createDocumentTypeDto,
                                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.BAD_REQUEST.getKey(), errors,
                    HttpStatusResponse.BAD_REQUEST.getCode());
        }
        try{
            DocumentType documentType = new DocumentType();
            documentType.setCode(createDocumentTypeDto.getCode());
            documentType.setName(createDocumentTypeDto.getName());
            documentType.setCountryCode(createDocumentTypeDto.getCountryCode());
            documentType.setIsActive(true);
            documentType.setCreatedAt(new Date());
            documentTypeRepository.save(documentType);
            DocumentTypeDto documentTypeDto = documentTypeMapper.toDocumentTypeDto(documentType);
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.CREATED.getKey(), documentTypeDto,
                    HttpStatusResponse.CREATED.getCode());
        }catch (Exception e ){
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(),
                    Collections.singletonList(e.getMessage()), HttpStatusResponse.INTERNAL_ERROR.getCode());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateDocumentType(UUID id, UpdateDocumentTypeDto updateDocumentTypeDto,
                                                                  BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.BAD_REQUEST.getKey(), errors,
                    HttpStatusResponse.BAD_REQUEST.getCode());
        }

        Optional<DocumentType> existingUser = documentTypeRepository.findById(id);
        if (existingUser.isEmpty()) {
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(), Collections.emptyList(),
                    HttpStatusResponse.NOT_FOUND.getCode());
        }
        try{
            DocumentType documentType = existingUser.get();
            documentType.setCode(updateDocumentTypeDto.getCode());
            documentType.setName(updateDocumentTypeDto.getName());
            documentType.setCountryCode(updateDocumentTypeDto.getCountryCode());
            documentType.setLastModified(new Date());
            documentTypeRepository.save(documentType);
            DocumentTypeDto documentTypeDto = documentTypeMapper.toDocumentTypeDto(documentType);
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.UPDATED.getKey(), documentTypeDto,
                    HttpStatusResponse.UPDATED.getCode());
        }catch (Exception e){
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(),
                    Collections.singletonList(e.getMessage()), HttpStatusResponse.INTERNAL_ERROR.getCode());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getDocumentTypeById(UUID id) {
        try {
            Optional<DocumentType> documentTypeOptional = documentTypeRepository.findById(id);
            if (documentTypeOptional.isEmpty()) {
                return ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(),
                        Collections.emptyList(), HttpStatusResponse.NOT_FOUND.getCode());
            }
            DocumentTypeDto documentTypeDto = documentTypeMapper.toDocumentTypeDto(documentTypeOptional.get());
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.OK.getKey(), documentTypeDto,
                    HttpStatusResponse.OK.getCode());
        } catch (Exception e) {
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(),
                    Collections.singletonList(e.getMessage()), HttpStatusResponse.INTERNAL_ERROR.getCode());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteDocumentType(UUID id) {
        Optional<DocumentType> existingDocumentType = documentTypeRepository.findById(id);
        if(existingDocumentType.isEmpty()){
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(),
                    Collections.emptyList(), HttpStatusResponse.NOT_FOUND.getCode());
        }
        try{
                DocumentType documentTypeEntity = existingDocumentType.get();
                documentTypeEntity.setIsActive(false);
                documentTypeRepository.save(documentTypeEntity);
                DocumentTypeDto documentTypeDto = documentTypeMapper.toDocumentTypeDto(existingDocumentType.get());
                return ResponseEntityCustom.builderResponse(HttpStatusResponse.DELETED.getKey(), documentTypeDto,
                        HttpStatusResponse.DELETED.getCode());
            }
        catch (Exception e){
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(),
                    Collections.singletonList(e.getMessage()), HttpStatusResponse.INTERNAL_ERROR.getCode());
        }
    }
}
