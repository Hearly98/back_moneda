package com.moneda.user_microservice.documenttype.entity;

import com.moneda.user_microservice.models.dto.AuditAware;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Table(name="Tipo_Documento")
@Entity
public class DocumentType extends AuditAware {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_tipo_documento")
    private UUID id;
    @Column(name = "codigo", length = 50, nullable = false)
    private String code;
    @Column(name = "nombre", length = 50, nullable = false)
    private String name;

    @Column(name = "codigo_pais", nullable = false)
    private String countryCode;
}

