package com.moneda.bankaccount.currency.repositories;

import com.moneda.bankaccount.currency.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, UUID> {
    List<Currency> findByIsActiveTrue();
}
