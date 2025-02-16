package com.moneda.bankaccount.interbankidentifiertype.repositories;

import com.moneda.bankaccount.interbankidentifiertype.entities.InterbankIdentifierType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface InterbankIdentifierTypeRepository extends JpaRepository<InterbankIdentifierType, UUID> {
    Optional<InterbankIdentifierType> findByCode(String code);
    List<InterbankIdentifierType> findByIsActiveTrue();
}
