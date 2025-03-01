package com.moneda.user_microservice.documenttype.mappers;

import com.moneda.user_microservice.documenttype.dto.DocumentTypeDto;
import com.moneda.user_microservice.documenttype.entity.DocumentType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DocumentTypeMapper {


    public DocumentTypeDto toDocumentTypeDto(DocumentType documentType){




        DocumentTypeDto dto = new DocumentTypeDto();
        dto.setId(documentType.getId());
        dto.setCode(documentType.getCode());
        dto.setName(documentType.getName());
        dto.setCountryCode(documentType.getCountryCode());

        return dto;


    }




}
