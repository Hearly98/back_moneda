package com.moneda.user_microservice.user.service;

import com.moneda.user_microservice.user.dto.CreateUserDto;
import com.moneda.user_microservice.user.dto.UpdateUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.UUID;

public interface UserService {

    ResponseEntity<Map<String, Object>> listUsers();
    ResponseEntity<Map<String, Object>> saveUser(CreateUserDto createUserDto, BindingResult bindingResult);
    ResponseEntity<Map<String, Object>> updateUser(UUID id, UpdateUserDto updateUserDto, BindingResult bindingResult);
    ResponseEntity<Map<String, Object>> getUserById(UUID id);
    ResponseEntity<Map<String, Object>> deleteUser(UUID id);
}
