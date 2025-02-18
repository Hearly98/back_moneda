package com.moneda.user_microservice.common.user.controller;

import com.moneda.user_microservice.common.user.dto.CreateUserDto;
import com.moneda.user_microservice.common.user.dto.UpdateUserDto;
import com.moneda.user_microservice.common.user.dto.UserDto;
import com.moneda.user_microservice.common.user.entity.User;
import com.moneda.user_microservice.common.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;


import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;





@Controller
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Obtiene todos los Usuarios registrados")
    @ApiResponse(responseCode = "200", description = "Listado de usuarios activos",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class)))
    @GetMapping("/selectCombo")
    public ResponseEntity<Map<String, Object>> listUsers() {
        return userService.listUsers();
    }



    @Operation(summary = "Registra un nuevo Usuario")
    @ApiResponse(responseCode = "200", description = "Usuario registrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class)))
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveUser(@RequestBody CreateUserDto createUserDto, BindingResult bindingResult) {
        return userService.saveUser(createUserDto, bindingResult);
    }




    @Operation(summary = "Actualiza un Usuario por Id")
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable UUID id, @RequestBody UpdateUserDto updateUserDto, BindingResult bindingResult){
        return userService.updateUser(id, updateUserDto, bindingResult);
    }





    @Operation(summary = "Elimina un Usuario por Id (Eliminación Logica)")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable UUID id) {
        return userService.deleteUser(id);
    }

    @Operation(summary = "Obtiene un Usuario por Id")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }





}
