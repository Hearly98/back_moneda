package com.moneda.bankAccount_microservice.common.interbankIdentifierType.controllers;

import com.moneda.bankAccount_microservice.common.bankAccountType.dto.BankAccountTypeDto;
import com.moneda.bankAccount_microservice.common.interbankIdentifierType.dto.CreateInterbankIdentifierTypeDto;
import com.moneda.bankAccount_microservice.common.interbankIdentifierType.dto.InterbankIdentifierTypeDto;
import com.moneda.bankAccount_microservice.common.interbankIdentifierType.dto.UpdateInterbankIdentifierTypeDto;
import com.moneda.bankAccount_microservice.common.interbankIdentifierType.service.InterbankIdentifierTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/tipo_identificador_interbancario")
@AllArgsConstructor
public class InterbankIdentifierTypeController {
    private final InterbankIdentifierTypeService interbankIdentifierTypeService;
    @ApiResponse(responseCode = "200", description = "Listado de Tipos de Identificador Interbancario",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = InterbankIdentifierTypeDto.class)))
    @Operation(summary = "Obtiene todos los Tipos de Identificador Interbancario registrados")
    @GetMapping("/selectCombo")
    public ResponseEntity<Map<String, Object>> listInterbankIdentifierType(){
        return interbankIdentifierTypeService.getAllInterbankIdentifierTypes();
    }
    @ApiResponse(responseCode = "200", description = "Identificador Interbancario creado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = InterbankIdentifierTypeDto.class)))
    @Operation(summary = "Crea un Identificador Interbancario")
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveInterbankIdentifierType
            (@RequestBody CreateInterbankIdentifierTypeDto createInterbankIdentifierTypeDto, BindingResult result){
        return interbankIdentifierTypeService.createInterbankIdentifierType(createInterbankIdentifierTypeDto, result);
    }
    @ApiResponse(responseCode = "200", description = "Identificador Interbancario actualizado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = InterbankIdentifierTypeDto.class)))
    @Operation(summary = "Actualiza un Identificador Interbancario")
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateInterbankIdentifierType
            (@PathVariable UUID id, @RequestBody UpdateInterbankIdentifierTypeDto updateInterbankIdentifierTypeDto, BindingResult result){
        return interbankIdentifierTypeService.updateInterbankIdentifierType(id, updateInterbankIdentifierTypeDto, result);
    }
    @ApiResponse(responseCode = "200", description = "Identificador Interbancario encontrado por Id",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = InterbankIdentifierTypeDto.class)))
    @Operation(summary = "Encuentra un Tipo de Identificador Interbancario por Id")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Map<String, Object>> getInterbankIdentifierTypeById(@PathVariable UUID id){
        return interbankIdentifierTypeService.getInterbankIdentifierTypeById(id);
    }
    @ApiResponse(responseCode = "200", description = "Identificador Interbancario eliminado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = InterbankIdentifierTypeDto.class)))
    @Operation(summary = "Elimina un Tipo de Identificador Interbancario por Id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteInterbankIdentifierTypeById(@PathVariable UUID id){
        return interbankIdentifierTypeService.deleteInterbankIdentifierType(id);
    }

}
