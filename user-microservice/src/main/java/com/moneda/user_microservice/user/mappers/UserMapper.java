package com.moneda.user_microservice.user.mappers;

import com.moneda.user_microservice.documenttype.mappers.DocumentTypeMapper;
import com.moneda.user_microservice.user.dto.UserDto;
import com.moneda.user_microservice.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class UserMapper {


    private final DocumentTypeMapper documentTypeMapper;

public UserDto toUserDto(User user){
UserDto dto= new UserDto();
dto.setId(user.getId());
dto.setFirstName(user.getFirstName());
dto.setLastName(user.getLastName());
dto.setNumDocument(user.getNumDocument());
dto.setBirthDate(user.getBirthDate());
dto.setPhone(user.getPhone());
dto.setEmail(user.getEmail());
dto.setCountryCode(user.getCountryCode());
dto.setDocumentTypeId(user.getDocumentType().getId());
    return dto;
}

}
