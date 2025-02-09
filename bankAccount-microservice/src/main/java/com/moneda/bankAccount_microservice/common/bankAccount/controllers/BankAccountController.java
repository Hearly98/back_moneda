package com.moneda.bankAccount_microservice.common.bankAccount.controllers;

import com.moneda.bankAccount_microservice.common.bankAccount.dto.CreateBankAccountDto;
import com.moneda.bankAccount_microservice.common.bankAccount.dto.UpdateBankAccountDto;
import com.moneda.bankAccount_microservice.common.bankAccount.service.BankAccountService;
import com.moneda.bankAccount_microservice.common.interbankIdentifierType.dto.InterbankIdentifierTypeDto;
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
@RequestMapping("/api/cuenta_banco")
@AllArgsConstructor
public class BankAccountController {
    private final BankAccountService bankAccountService;
    @ApiResponse(responseCode = "200", description = "Listado de Cuentas de Banco",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = InterbankIdentifierTypeDto.class)))
    @Operation(summary = "Obtiene todos las Cuentas de Banco registrados")
    @GetMapping("/selectCombo")
    public ResponseEntity<Map<String, Object>> listBankAccount(){
        return bankAccountService.listBankAccounts();
    }
    @ApiResponse(responseCode = "200", description = "Cuenta de Banco registrada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = InterbankIdentifierTypeDto.class)))
    @Operation(summary = "Crea una Cuenta Banco")
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveBankAccount(@RequestBody CreateBankAccountDto createBankAccountDto,
                                                               BindingResult result){
        return bankAccountService.saveBankAccount(createBankAccountDto, result);
    }
    @ApiResponse(responseCode = "200", description = "Cuenta de Banco actualizada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = InterbankIdentifierTypeDto.class)))
    @Operation(summary = "Actualiza la Cuenta de Banco")
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateBankAccount(@PathVariable UUID id,
                                                                 @RequestBody UpdateBankAccountDto updateBankAccountDto,
                                                                 BindingResult result){
        return bankAccountService.updateBankAccount(id, updateBankAccountDto, result);
    }
    @ApiResponse(responseCode = "200", description = "Cuenta de Banco encontrado por Id",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = InterbankIdentifierTypeDto.class)))
    @Operation(summary = "Encuentra una Cuenta de Banco por Id")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Map<String, Object>> getBankAccountById(@PathVariable UUID id){
        return bankAccountService.getBankAccountById(id);
    }
    @ApiResponse(responseCode = "200", description = "Cuenta de Banco encontrado por Número Interbancario",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = InterbankIdentifierTypeDto.class)))
    @Operation(summary = "Encuentra una Cuenta de Banco por Número Interbancario")
    @GetMapping("/get-by-interbankNumber/{interbankNumber}")
    public ResponseEntity<Map<String, Object>> getBankAccountById(@PathVariable String interbankNumber){
        return bankAccountService.getBankAccountByInterbankNumber(interbankNumber);
    }
    @ApiResponse(responseCode = "200", description = "Cuenta de Banco eliminado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = InterbankIdentifierTypeDto.class)))
    @Operation(summary = "Elimina una Cuenta de Banco por Id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteBankAccountById(@PathVariable UUID id){
        return bankAccountService.deleteBankAccount(id);
    }
}
