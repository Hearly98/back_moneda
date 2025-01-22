package com.moneda.bankAccount_microservice.common.currency.repositories;

import com.moneda.bankAccount_microservice.common.currency.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, UUID> {
}
