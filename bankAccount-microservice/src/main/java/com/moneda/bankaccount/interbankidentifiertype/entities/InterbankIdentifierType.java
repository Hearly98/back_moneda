package com.moneda.bankaccount.interbankidentifiertype.entities;

import com.moneda.bankaccount.models.AuditAware;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@Table(name = "tipo_identificador_interbancario")
public class InterbankIdentifierType extends AuditAware {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_identificador_interbancario", nullable = false, unique = true)
    private UUID id;
    @Column(name = "codigo", nullable = false, unique = true)
    private String code;
    @Column(name = "codigo_pais", nullable = false)
    private String countryCode;
    @Column(name = "nombre", nullable = false)
    private String name;
}
