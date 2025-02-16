package com.moneda.bankaccount.bankaccounttype.repositories;

import com.moneda.bankaccount.bankaccounttype.entities.BankAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BankAccountTypeRepository extends JpaRepository<BankAccountType, UUID> {
    Optional<BankAccountType> findByCode(String code);
    List<BankAccountType> findByIsActiveTrue();
}