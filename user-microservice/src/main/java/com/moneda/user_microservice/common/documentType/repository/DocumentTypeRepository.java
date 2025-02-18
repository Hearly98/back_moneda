package com.moneda.user_microservice.common.documentType.repository;

import com.moneda.user_microservice.common.documentType.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, UUID> {

    List<DocumentType> findByIsActiveTrue();

}
