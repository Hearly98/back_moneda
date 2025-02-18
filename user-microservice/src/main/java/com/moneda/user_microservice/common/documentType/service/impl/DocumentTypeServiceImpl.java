package com.moneda.user_microservice.common.documentType.service.impl;

import com.moneda.user_microservice.common.documentType.dto.CreateDocumentTypeDto;
import com.moneda.user_microservice.common.documentType.dto.DocumentTypeDto;
import com.moneda.user_microservice.common.documentType.dto.UpdateDocumentTypeDto;
import com.moneda.user_microservice.common.documentType.entity.DocumentType;
import com.moneda.user_microservice.common.documentType.mappers.DocumentTypeMapper;
import com.moneda.user_microservice.common.documentType.repository.DocumentTypeRepository;
import com.moneda.user_microservice.common.documentType.service.DocumentTypeService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DocumentTypeServiceImpl implements DocumentTypeService {


    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentTypeMapper documentTypeMapper;


    @Override
    public ResponseEntity<Map<String, Object>> listDocumentType() {


        Map<String,Object> response= new HashMap<>();

        List<DocumentTypeDto> documentType =documentTypeRepository.findByIsActiveTrue()
                .stream()
                .map(documentTypeMapper::toDocumentTypeDto)
                .toList();
        if(documentType.isEmpty()){
            response.put("message", "No hay datos");
            response.put("DocumentType", Collections.emptyList());
        } else {
            response.put("message","Lista de Tipos de Documentos Activos");
            response.put("DocumentType",documentType);
        }

        return ResponseEntity.ok(response);
    }

    @Transactional
    @Override
    public ResponseEntity<Map<String, Object>> saveDocumentType(CreateDocumentTypeDto createDocumentTypeDto, BindingResult bindingResult) {

        Map<String,Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("message", "Error en la validación");
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }
        try{

            DocumentType documentType = new DocumentType();
            documentType.setCode(createDocumentTypeDto.getCode());
            documentType.setName(createDocumentTypeDto.getName());
            documentType.setCodPais(createDocumentTypeDto.getCodPais());

            documentTypeRepository.save(documentType);

            DocumentTypeDto documentTypeDto = documentTypeMapper.toDocumentTypeDto(documentType);
            response.put("message", "Tipo de Documento creado exitosamente");
            response.put("DocumentType", documentTypeDto);
            return ResponseEntity.ok(response);



        }catch (Exception e ){

            response.put("message", "Error al registrar el Tipo de Documento ");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }


    }

    @Override
    public ResponseEntity<Map<String, Object>> updateDocumentType(UUID id, UpdateDocumentTypeDto updateDocumentTypeDto, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("message", "Error en la validación");
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }

        Optional<DocumentType> existingUser = documentTypeRepository.findById(id);
        if (existingUser.isEmpty()) {
            response.put("message", "Tipo de Documento con ID '" + id + "' no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        try{

            DocumentType documentType = existingUser.get();
            documentType.setCode(documentType.getCode());
            documentType.setName(documentType.getName());
            documentType.setCodPais(documentType.getCodPais());

            documentTypeRepository.save(documentType);

            DocumentTypeDto documentTypeDto = documentTypeMapper.toDocumentTypeDto(documentType);
            response.put("message", "El Tipo de Documento se ha actualizado correctamente");
            response.put("DocumentType", documentTypeDto);
            return ResponseEntity.ok(response);



        }catch (Exception e){

            response.put("message", "Error al actualizar el Tipo de Documento");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getDocumentTypeById(UUID id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<DocumentType> DocumentTypeOptional = documentTypeRepository.findById(id);
            if (DocumentTypeOptional.isEmpty()) {
                response.put("message", "El Tipo de Documento no existe");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            response.put("message", "Tipo de Documento no encontrado");
            response.put("DocumentType", DocumentTypeOptional.get());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("message", "Error al buscar al Tipo de Documento");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteDocumentType(UUID id) {
        Map<String, Object> response = new HashMap<>();
        if(!documentTypeRepository.existsById(id)){
            response.put("message", "El Tipo de Documento no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        try{
            Optional<DocumentType> existingDocumentType = documentTypeRepository.findById(id);
            if (existingDocumentType.isPresent()) {
                DocumentType documentTypeEntity = existingDocumentType.get();
                documentTypeEntity.setIsActive(false);
                documentTypeRepository.save(documentTypeEntity);
                response.put("message", "El Tipo de Documento se eliminó exitosamente");
                response.put("DocumentType", documentTypeEntity);
                return ResponseEntity.ok(response);
            }
        }
        catch (Exception e){
            response.put("message", "Error al eliminar el Tipo de Documento");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        response.put("message", "Error inesperado");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
