package com.moneda.bankaccount.common.interbankIdentifierType.entities;

import com.moneda.bankaccount.common.models.AuditAware;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "tipo_identificador_interbancario")
public class InterbankIdentifierType extends AuditAware {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_identificador_interbancario", nullable = false)
    private UUID id;
    @Column(name = "codigo", nullable = false)
    private String code;
    @Column(name = "codigo_pais", nullable = false)
    private String countryCode;
    @Column(name = "nombre", nullable = false)
    private String name;
}
