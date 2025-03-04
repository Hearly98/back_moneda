package com.moneda.bankaccount.bankaccount.repositories;

import com.moneda.bankaccount.bankaccount.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {
    boolean existsByAccountNumber(String accountNumber);
    boolean existsByAlias(String alias);
    List<BankAccount> findByIsActiveTrue();
    Optional<BankAccount> findByInterbankNumber(String interbankNumber);
}
