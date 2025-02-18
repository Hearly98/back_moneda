package com.moneda.user_microservice.common.user.mappers;

import com.moneda.user_microservice.common.documentType.entity.DocumentType;
import com.moneda.user_microservice.common.documentType.mappers.DocumentTypeMapper;
import com.moneda.user_microservice.common.user.dto.UserDto;
import com.moneda.user_microservice.common.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@AllArgsConstructor
public class UserMapper {


    private final DocumentTypeMapper documentTypeMapper;

public UserDto toUserDto(User user){
UserDto dto= new UserDto();
dto.setFirstName(user.getFirstName());
dto.setLastName(user.getLastName());
dto.setAddress(user.getAddress());
dto.setNumDocument(user.getNumDocument());
dto.setBirthDate(user.getBirthDate());
dto.setPhone(user.getPhone());
dto.setEmail(user.getEmail());
dto.setPassword(user.getPassword());
dto.setCountryCode(user.getCountryCode());
dto.setDocumentTypeId(user.getDocumentType().getId());
    return dto;
}

}
