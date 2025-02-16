package com.moneda.bankaccount.common.interbankIdentifierType.repositories;

import com.moneda.bankaccount.common.interbankIdentifierType.entities.InterbankIdentifierType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface InterbankIdentifierTypeRepository extends JpaRepository<InterbankIdentifierType, UUID> {
    Optional<InterbankIdentifierType> findByCode(String code);
}
