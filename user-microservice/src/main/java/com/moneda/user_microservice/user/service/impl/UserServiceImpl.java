package com.moneda.user_microservice.user.service.impl;

import com.moneda.user_microservice.documenttype.entity.DocumentType;
import com.moneda.user_microservice.documenttype.repository.DocumentTypeRepository;
import com.moneda.user_microservice.user.dto.CreateUserDto;
import com.moneda.user_microservice.user.dto.UpdateUserDto;
import com.moneda.user_microservice.user.dto.UserDto;
import com.moneda.user_microservice.user.entity.User;
import com.moneda.user_microservice.user.mappers.UserMapper;
import com.moneda.user_microservice.user.repository.UserRepository;
import com.moneda.user_microservice.user.service.UserService;
import com.moneda.utils.HttpStatusResponse;
import com.moneda.utils.ResponseEntityCustom;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private  final DocumentTypeRepository documentTypeRepository;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<Map<String, Object>> listUsers() {
        List<UserDto> users =userRepository.findByIsActiveTrue()
                .stream()
                .map(userMapper::toUserDto)
                .toList();
        return users.isEmpty() ?
                ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(), Collections.emptyList(),
                        HttpStatusResponse.NOT_FOUND.getCode()) :
                ResponseEntityCustom.builderResponse(HttpStatusResponse.OK.getKey(), users,
                        HttpStatusResponse.OK.getCode());
    }

    @Transactional
    @Override
    public ResponseEntity<Map<String, Object>> saveUser(CreateUserDto createUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.BAD_REQUEST.getKey(), errors,
                    HttpStatusResponse.BAD_REQUEST.getCode());
        }

        try{
           User user= new User();
           DocumentType documentType= documentTypeRepository.findById(createUserDto.getDocumentTypeId())
                   .orElseThrow(()-> new RuntimeException("Tipo de Documento no encontrado"));
            user.setFirstName(createUserDto.getFirstName());
            user.setLastName(createUserDto.getLastName());
            user.setAddress(createUserDto.getAddress());
            user.setNumDocument(createUserDto.getNumDocument());
            user.setBirthDate(createUserDto.getBirthDate());
            user.setPhone(createUserDto.getPhone());
            user.setEmail(createUserDto.getEmail());
            user.setPassword(createUserDto.getPassword());
            user.setCountryCode(createUserDto.getCountryCode());
            user.setDocumentType(documentType);
            user.setIsActive(true);
            user.setCreatedAt(new Date());
            userRepository.save(user);
            UserDto userDto = userMapper.toUserDto(user);
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.CREATED.getKey(),
                    userDto, HttpStatusResponse.CREATED.getCode());
        }catch (Exception e ){
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(),
                    Collections.singletonList(e.getMessage()), HttpStatusResponse.INTERNAL_ERROR.getCode());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateUser(UUID id, UpdateUserDto updateUserDto,
                                                          BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.BAD_REQUEST.getKey(), errors,
                    HttpStatusResponse.BAD_REQUEST.getCode());
        }

        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(), Collections.emptyList(),
                    HttpStatusResponse.NOT_FOUND.getCode());
        }
        try{
            User user = existingUser.get();
            user.setFirstName(updateUserDto.getFirstName());
            user.setLastName(updateUserDto.getLastName());
            user.setAddress(updateUserDto.getAddress());
            user.setBirthDate(updateUserDto.getBirthDate());
            user.setPhone(updateUserDto.getPhone());
            userRepository.save(user);
            UserDto userDto = userMapper.toUserDto(user);
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.UPDATED.getKey(), userDto,
                    HttpStatusResponse.UPDATED.getCode());
        } catch (Exception e) {
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(),
                    Collections.singletonList(e.getMessage()), HttpStatusResponse.INTERNAL_ERROR.getCode());
        }

    }

    @Override
    public ResponseEntity<Map<String, Object>> getUserById(UUID id) {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isEmpty()) {
                return ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(),
                        Collections.emptyList(), HttpStatusResponse.NOT_FOUND.getCode());
            }
            UserDto userDto = userMapper.toUserDto(userOptional.get());
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.OK.getKey(), userDto,
                    HttpStatusResponse.OK.getCode());
        } catch (Exception e) {
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(),
                    Collections.singletonList(e.getMessage()), HttpStatusResponse.INTERNAL_ERROR.getCode());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteUser(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.NOT_FOUND.getKey(),
                    Collections.emptyList(), HttpStatusResponse.NOT_FOUND.getCode());
        }
        try{
                User userEntity = userOptional.get();
                userEntity.setIsActive(false);
                userRepository.save(userEntity);
                UserDto userDto = userMapper.toUserDto(userOptional.get());
                return ResponseEntityCustom.builderResponse(HttpStatusResponse.DELETED.getKey(), userDto,
                HttpStatusResponse.DELETED.getCode());
        }
        catch (Exception e){
            return ResponseEntityCustom.builderResponse(HttpStatusResponse.INTERNAL_ERROR.getKey(),
                    Collections.singletonList(e.getMessage()), HttpStatusResponse.INTERNAL_ERROR.getCode());
        }
    }
}
