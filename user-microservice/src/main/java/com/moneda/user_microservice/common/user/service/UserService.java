package com.moneda.user_microservice.common.user.service;

import com.moneda.user_microservice.common.user.dto.CreateUserDto;
import com.moneda.user_microservice.common.user.dto.UpdateUserDto;
import com.moneda.user_microservice.common.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.UUID;

public interface UserService {

    public ResponseEntity<Map<String, Object>> listUsers();
    public ResponseEntity<Map<String, Object>> saveUser(CreateUserDto createUserDto, BindingResult bindingResult);
    public ResponseEntity<Map<String, Object>> updateUser(UUID id, UpdateUserDto updateUserDto, BindingResult bindingResult);
    public ResponseEntity<Map<String, Object>> getUserById(UUID id);
    public ResponseEntity<Map<String, Object>> deleteUser(UUID id);
}
