package com.moneda.bankAccount_microservice.common.bankAccountType.entities;

import com.moneda.bankAccount_microservice.common.currency.entities.Currency;
import com.moneda.bankAccount_microservice.common.models.AuditAware;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
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
    @Column(name = "nombre", length = 250, nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_moneda", nullable = false)
    private Currency currency;
}