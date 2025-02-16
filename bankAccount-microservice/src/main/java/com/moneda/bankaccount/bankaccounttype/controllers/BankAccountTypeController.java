package com.moneda.bankaccount.bankaccounttype.controllers;

import com.moneda.bankaccount.bankaccounttype.dto.BankAccountTypeDto;
import com.moneda.bankaccount.bankaccounttype.dto.CreateBankAccountTypeDto;
import com.moneda.bankaccount.bankaccounttype.dto.UpdateBankAccountTypeDto;
import com.moneda.bankaccount.bankaccounttype.service.BankAccountTypeService;

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
@RequestMapping("/api/tipo_cuenta_banco")
@AllArgsConstructor
public class BankAccountTypeController {
    private final BankAccountTypeService bankAccountTypeService;
    @ApiResponse(responseCode = "200", description = "Listado de Tipos de Cuenta Banco",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BankAccountTypeDto.class)))
    @Operation(summary = "Obtiene todos los Tipos de Cuenta Banco registrados")
    @GetMapping("/selectCombo")
    public ResponseEntity<Map<String, Object>> listBankAccountTypes(){
        return bankAccountTypeService.listBankAccountTypes();
    }
    @ApiResponse(responseCode = "200", description = "Cuenta Banco registrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BankAccountTypeDto.class)))
    @Operation(summary = "Registra un nuevo Tipo Cuenta Banco")
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveCurrency(@RequestBody CreateBankAccountTypeDto createBankAccountTypeDto, BindingResult result){
        return bankAccountTypeService.saveBankAccountType(createBankAccountTypeDto, result);
    }
    @ApiResponse(responseCode = "200", description = "Cuenta Banco actualizada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BankAccountTypeDto.class)))
    @Operation(summary = "Actualiza un Tipo Cuenta Banco por Id")
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateCurrency(@PathVariable String id, @RequestBody UpdateBankAccountTypeDto updateBankAccountTypeDto, BindingResult result){
        if (id.startsWith("0x")) {
            id = id.substring(2);
        }
        UUID uuid = UUID.fromString(id);

        return bankAccountTypeService.updateBankAccountType(uuid, updateBankAccountTypeDto, result);
    }
    @ApiResponse(responseCode = "200", description = "Cuenta Banco eliminado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BankAccountTypeDto.class)))
    @Operation(summary = "Elimina un Tipo Cuenta Banco por Id (Eliminación Logica)")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteCurrency(@PathVariable UUID id){
        return bankAccountTypeService.deleteBankAccountType(id);
    }
    @ApiResponse(responseCode = "200", description = "Cuenta Banco encontrado por Id",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BankAccountTypeDto.class)))
    @Operation(summary = "Obtiene un Tipo Cuenta Banco por Id")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Map<String, Object>> getCurrencyById(@PathVariable UUID id){
        return bankAccountTypeService.getBankAccountTypeById(id);
    }
}
