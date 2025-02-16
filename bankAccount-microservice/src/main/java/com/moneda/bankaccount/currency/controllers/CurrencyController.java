package com.moneda.bankaccount.currency.controllers;

import com.moneda.bankaccount.currency.dto.CreateCurrencyDto;
import com.moneda.bankaccount.currency.dto.CurrencyDto;
import com.moneda.bankaccount.currency.dto.UpdateCurrencyDto;
import com.moneda.bankaccount.currency.service.CurrencyService;
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
@RequestMapping("/api/moneda")
@AllArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;
    @Operation(summary = "Obtiene todos los tipos de moneda registrados")
    @ApiResponse(responseCode = "200", description = "Listado de monedas",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CurrencyDto.class)))
    @GetMapping("/selectCombo")
    public ResponseEntity<Map<String, Object>> getCurrencies(){
        return currencyService.getCurrencies();
    }
    @Operation(summary = "Registrar un Tipo Moneda")
    @ApiResponse(responseCode = "200", description = "Tipo moneda creada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CurrencyDto.class)))
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveCurrency(@RequestBody CreateCurrencyDto createCurrencyDto, BindingResult result){
        return currencyService.saveCurrency(createCurrencyDto, result);
    }
    @Operation(summary = "Actualizar Tipo Moneda por Id")
    @ApiResponse(responseCode = "200", description = "Tipo moneda actualizada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CurrencyDto.class)))
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateCurrency(@PathVariable UUID id, @RequestBody UpdateCurrencyDto currencyDto, BindingResult result){
        return currencyService.updateCurrency(id, currencyDto, result);
    }
    @Operation(summary = "Eliminación Logica de Tipo Moneda por Id, cambia el estado active a false")
    @ApiResponse(responseCode = "200", description = "Tipo Moneda eliminada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CurrencyDto.class)))
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteCurrency(@PathVariable UUID id){
        return currencyService.deleteCurrency(id);
    }
    @Operation(summary = "Obtiene el nombre y codigo del id tipo de moneda")
    @ApiResponse(responseCode = "200", description = "Tipo Moneda encontrado por Id",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CurrencyDto.class)))
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Map<String, Object>> getCurrencyById(@PathVariable UUID id){
        return currencyService.getCurrencyById(id);
    }

}
