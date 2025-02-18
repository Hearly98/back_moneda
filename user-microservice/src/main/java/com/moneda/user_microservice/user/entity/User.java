package com.moneda.user_microservice.user.entity;

import com.moneda.user_microservice.documenttype.entity.DocumentType;
import com.moneda.user_microservice.models.dto.AuditAware;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "usuarios")
public class User extends AuditAware {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_cuenta_user")
    private UUID id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String firstName;

    @Column(name = "apellidos", length = 50, nullable = false)
    private String lastName;

    @Column(name = "domicilio")
    private String address;

    @Column(name="numero_documento", nullable = false)
    private String numDocument;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date birthDate;

    @Column(name = "telefono")
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", columnDefinition = "CHAR(6)", nullable = false)
    private String password;

    @Column(name = "codigo_pais", nullable = false)
    private String countryCode;


    @ManyToOne
    @JoinColumn(name = "id_tipo_documento")
    private DocumentType documentType;









}
