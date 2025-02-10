package com.moneda.bankAccount_microservice.common.bankAccount.entities;

import com.moneda.bankAccount_microservice.common.bankAccountType.entities.BankAccountType;
import com.moneda.bankAccount_microservice.common.interbankIdentifierType.entities.InterbankIdentifierType;
import com.moneda.bankAccount_microservice.common.models.AuditAware;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "cuenta_banco")
@Data
@Entity
public class BankAccount extends AuditAware {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_cuenta_banco", nullable = false)
    private UUID id;
    @Column(name = "saldo", nullable = false)
    private BigDecimal balance;
    @Column(name = "id_usuario", nullable = false)
    private UUID userId;
    @Column(name = "cuenta_banco", nullable = false, length = 20)
    private String accountNumber;
    @Column(name= "numero_interbancario", nullable = false)
    private String interbankNumber;
    @Column(name = "alias", nullable = false, length = 20)
    private String alias;
    @ManyToOne
    @JoinColumn(name = "id_identificador_interbancario", nullable = false)
    private InterbankIdentifierType interbankIdentifierType;
    @ManyToOne
    @JoinColumn(name = "id_tipo_cuenta_banco", nullable = false)
    private BankAccountType bankAccountType;
}
