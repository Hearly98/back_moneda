package com.moneda.bankaccount.common.bankAccountType.entities;

import com.moneda.bankaccount.common.currency.entities.Currency;
import com.moneda.bankaccount.common.models.AuditAware;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Table(name = "tipo_cuenta_banco")
@Data
@Entity
public class BankAccountType extends AuditAware {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_tipo_cuenta_banco")
    private UUID id;
    @Column(name = "codigo", length = 250, nullable = false)
    private String code;
    @Column(name = "codigo_pais", length = 250, nullable = false)
    private String countryCode;
    @Column(name = "nombre", length = 250, nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_moneda", nullable = false)
    private Currency currency;
}