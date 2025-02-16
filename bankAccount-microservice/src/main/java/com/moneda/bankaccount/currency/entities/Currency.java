package com.moneda.bankaccount.currency.entities;

import com.moneda.bankaccount.models.AuditAware;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;
@EqualsAndHashCode(callSuper = false)
@Table(name = "moneda")
@Data
@Entity
public class Currency extends AuditAware {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_tipo_moneda")
    private UUID id;
    @Column(name = "nombre", length = 100, nullable = false)
    private String name;
    @Column(name = "codigo", length = 100, nullable = false)
    private String code;
    @Column(name = "simbolo", nullable = false)
    private String symbol;
}
