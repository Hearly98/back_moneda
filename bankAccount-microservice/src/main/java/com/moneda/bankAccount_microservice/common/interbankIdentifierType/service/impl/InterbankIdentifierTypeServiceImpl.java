package com.moneda.bankAccount_microservice.common.interbankIdentifierType.service.impl;
import com.moneda.bankAccount_microservice.common.interbankIdentifierType.dto.CreateInterbankIdentifierTypeDto;
import com.moneda.bankAccount_microservice.common.interbankIdentifierType.dto.InterbankIdentifierTypeDto;
import com.moneda.bankAccount_microservice.common.interbankIdentifierType.dto.UpdateInterbankIdentifierTypeDto;
import com.moneda.bankAccount_microservice.common.interbankIdentifierType.entities.InterbankIdentifierType;
import com.moneda.bankAccount_microservice.common.interbankIdentifierType.mappers.InterbankIdentifierTypeMapper;
import com.moneda.bankAccount_microservice.common.interbankIdentifierType.repositories.InterbankIdentifierTypeRepository;
import com.moneda.bankAccount_microservice.common.interbankIdentifierType.service.InterbankIdentifierTypeService;
import com.moneda.utils.HttpStatusResponse;
import com.moneda.utils.ResponseEntityCustom;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.*;

@Service
@AllArgsConstructor
public class InterbankIdentifierTypeServiceImpl implements InterbankIdentifierTypeService {
    private final InterbankIdentifierTypeRepository interbankIdentifierTypeRepository;
    private final InterbankIdentifierTypeMapper interbankIdentifierTypeMapper;

    @Override
    public ResponseEntity<Map<String, Object>> getAllInterbankIdentifierTypes() {
        List<InterbankIdentifierTypeDto> identifiers = interbankIdentifierTypeRepository.findAll()
                .stream()
                .map(interbankIdentifierTypeMapper::toInterbankIdentifierTypeDto)
                .toList();
        return identifiers.isEmpty() ?
                ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(), Collections.emptyList(), HttpStatusResponse.NOT_FOUND.getCode()):
                ResponseEntityCustom.builderResponse(HttpStatusResponse.OK.getKey(), identifiers, HttpStatusResponse.OK.getCode());
    }

    @Override
    public ResponseEntity<Map<String, Object>> createInterbankIdentifierType(CreateInterbankIdentifierTypeDto identifier, BindingResult result) {
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.BAD_REQUEST.getKey(), errors, HttpStatusResponse.BAD_REQUEST.getCode());
        }
        try{
            InterbankIdentifierType identifierType = new InterbankIdentifierType();
            identifierType.setCode(identifier.getCode());
            identifierType.setName(identifier.getName());
            identifierType.setCountryCode(identifier.getCountryCode());
            identifierType.setCreatedAt(new Date());
            identifierType.setIsActive(true);
            interbankIdentifierTypeRepository.save(identifierType);
            InterbankIdentifierTypeDto identifierTypeDto = interbankIdentifierTypeMapper.toInterbankIdentifierTypeDto(identifierType);
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.OK.getKey(), identifierTypeDto, HttpStatusResponse.OK.getCode());
        }catch (Exception e){
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(), e.getMessage(), HttpStatusResponse.INTERNAL_ERROR.getCode());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateInterbankIdentifierType(UUID id, UpdateInterbankIdentifierTypeDto identifier, BindingResult result) {
        if(result.hasErrors()){
          List<String> errors = result.getFieldErrors().stream()
                  .map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage())
                  .toList();
          return ResponseEntityCustom.builderResponse(HttpStatusResponse.BAD_REQUEST.getKey(), errors, HttpStatusResponse.BAD_REQUEST.getCode());
        }
        Optional<InterbankIdentifierType> existingIdentifier = interbankIdentifierTypeRepository.findById(id);
        if(existingIdentifier.isEmpty()){
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(), null, HttpStatusResponse.NOT_FOUND.getCode());
        }
        try {
            InterbankIdentifierType identifierType = existingIdentifier.get();
            identifierType.setName(identifier.getName());
            identifierType.setCode(identifier.getCode());
            identifierType.setCountryCode(identifier.getCountryCode());
            identifierType.setLastModified(new Date());
            interbankIdentifierTypeRepository.save(identifierType);
            InterbankIdentifierTypeDto identifierTypeDto = interbankIdentifierTypeMapper.toInterbankIdentifierTypeDto(identifierType);
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.OK.getKey(), identifierTypeDto, HttpStatusResponse.OK.getCode());
        }catch (Exception e) {
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(), e.getMessage(), HttpStatusResponse.INTERNAL_ERROR.getCode());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getInterbankIdentifierTypeById(UUID id) {
      try{
          Optional<InterbankIdentifierType> existingIdentifier = interbankIdentifierTypeRepository.findById(id);
          if(existingIdentifier.isEmpty()){
              return ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(), null, HttpStatusResponse.NOT_FOUND.getCode());
          }
          InterbankIdentifierTypeDto identifierTypeDto = interbankIdentifierTypeMapper.toInterbankIdentifierTypeDto(existingIdentifier.get());
          return ResponseEntityCustom.builderResponse(HttpStatusResponse.OK.getKey(), identifierTypeDto, HttpStatusResponse.OK.getCode());
      }catch (Exception e){
          return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(), e.getMessage(), HttpStatusResponse.INTERNAL_ERROR.getCode());
      }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteInterbankIdentifierType(UUID id) {
        if(!interbankIdentifierTypeRepository.existsById(id)){
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(), null, HttpStatusResponse.NOT_FOUND.getCode());
        }
        try{
            Optional<InterbankIdentifierType> existingIdentifier = interbankIdentifierTypeRepository.findById(id);
            if(existingIdentifier.isEmpty()){
                return ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(), null, HttpStatusResponse.NOT_FOUND.getCode());
            }
            InterbankIdentifierType identifierType = existingIdentifier.get();
            identifierType.setIsActive(false);
            interbankIdentifierTypeRepository.save(identifierType);
            InterbankIdentifierTypeDto identifierTypeDto = interbankIdentifierTypeMapper.toInterbankIdentifierTypeDto(identifierType);
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.OK.getKey(), identifierTypeDto, HttpStatusResponse.OK.getCode());
        }
        catch (Exception e){
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(), e.getMessage(), HttpStatusResponse.INTERNAL_ERROR.getCode());
        }
    }
}
