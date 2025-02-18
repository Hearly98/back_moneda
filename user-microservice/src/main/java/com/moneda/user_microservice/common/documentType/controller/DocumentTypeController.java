package com.moneda.user_microservice.common.documentType.controller;

import com.moneda.user_microservice.common.documentType.dto.CreateDocumentTypeDto;
import com.moneda.user_microservice.common.documentType.dto.DocumentTypeDto;
import com.moneda.user_microservice.common.documentType.dto.UpdateDocumentTypeDto;
import com.moneda.user_microservice.common.documentType.service.DocumentTypeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/api/documentType")
@AllArgsConstructor
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;


    @Operation(summary = "Obtiene todos los Tipos de Documentos registrados")
    @ApiResponse(responseCode = "200", description = "Listado de tipos de documentos activos",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DocumentTypeDto.class)))
    @GetMapping("/selectCombo")
    public ResponseEntity<Map<String, Object>> listDocumentType() {
        return documentTypeService.listDocumentType();
    }



    @Operation(summary = "Registra un nuevo Tipo de Documento")
    @ApiResponse(responseCode = "200", description = "Tipo de Documento registrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DocumentTypeDto.class)))
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveDocumentType(@RequestBody CreateDocumentTypeDto createDocumentTypeDto, BindingResult bindingResult) {
        return documentTypeService.saveDocumentType(createDocumentTypeDto, bindingResult);
    }




    @Operation(summary = "Actualiza un Tipo de Documento por Id")
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateDocumentType(@PathVariable UUID id, @RequestBody UpdateDocumentTypeDto updateDocumentTypeDto, BindingResult bindingResult){
        return documentTypeService.updateDocumentType(id, updateDocumentTypeDto, bindingResult);
    }





    @Operation(summary = "Elimina un Tipo de Documento por Id (Eliminación Logica)")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteDocumentType(@PathVariable UUID id) {
        return documentTypeService.deleteDocumentType(id);
    }

    @Operation(summary = "Obtiene un Tipo de Documento por Id")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Map<String, Object>> getDocumentTypeById(@PathVariable UUID id) {
        return documentTypeService.getDocumentTypeById(id);
    }







}
