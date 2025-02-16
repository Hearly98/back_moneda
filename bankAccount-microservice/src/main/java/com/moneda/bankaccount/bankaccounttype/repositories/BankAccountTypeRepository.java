package com.moneda.bankaccount.common.bankAccountType.repositories;

import com.moneda.bankaccount.common.bankAccountType.entities.BankAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BankAccountTypeRepository extends JpaRepository<BankAccountType, UUID> {
    Optional<BankAccountType> findByCode(String code);
}
