package com.moneda.bankAccount_microservice.common.interbankIdentifierType.repositories;

import com.moneda.bankAccount_microservice.common.interbankIdentifierType.entities.InterbankIdentifierType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface InterbankIdentifierTypeRepository extends JpaRepository<InterbankIdentifierType, UUID> {
}
