package com.moneda.bankaccount.common.currency.repositories;

import com.moneda.bankaccount.common.currency.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, UUID> {
}
