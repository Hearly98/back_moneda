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
import org.springframework.http.HttpStatus;
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

          Map<String,Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();
            response.put("message", "Error en la validación");
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
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


            userRepository.save(user);

            UserDto userDto = userMapper.toUserDto(user);
            response.put("message", "Usuario creado exitosamente");
            response.put("user", userDto);
            return ResponseEntity.ok(response);



        }catch (Exception e ){

            response.put("message", "Error al registrar el usuario ");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }


    }

    @Override
    public ResponseEntity<Map<String, Object>> updateUser(UUID id, UpdateUserDto updateUserDto, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(err->"El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();
            response.put("message", "Error en la validación");
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }

        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            response.put("message", "Usuario con ID '" + id + "' no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
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
            response.put("message", "El usuario se ha actualizado correctamente");
            response.put("User", userDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error al actualizar el Usuario");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @Override
    public ResponseEntity<Map<String, Object>> getUserById(UUID id) {


        Map<String, Object> response = new HashMap<>();
        try {
            Optional<User> UserOptional = userRepository.findById(id);
            if (UserOptional.isEmpty()) {
                response.put("message", "El usuario no existe");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            response.put("message", "Usuario no encontrado");
            response.put("User", UserOptional.get());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("message", "Error al buscar al Usuario");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteUser(UUID id) {



        Map<String, Object> response = new HashMap<>();
        if(!userRepository.existsById(id)){
            response.put("message", "El usuario no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        try{
            Optional<User> existingUser = userRepository.findById(id);
            if (existingUser.isPresent()) {
                User userEntity = existingUser.get();
                userEntity.setIsActive(false);
                userRepository.save(userEntity);
                response.put("message", "El usuario se eliminó exitosamente");
                response.put("bankAccount", userEntity);
                return ResponseEntity.ok(response);
            }
        }
        catch (Exception e){
            response.put("message", "Error al eliminar el Usuario");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        response.put("message", "Error inesperado");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);


    }
}
