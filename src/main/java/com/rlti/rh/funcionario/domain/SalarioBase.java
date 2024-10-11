package com.rlti.rh.funcionario.domain;

import com.rlti.rh.contrato.domain.Cargo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.rlti.rh.funcionario.application.api.request.SalarioBaseRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "SALARIO_BASE")
public class SalarioBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salario_base_seq_generator")
    @SequenceGenerator(name="salario_base_seq_generator", sequenceName = "salario_base_sequence", allocationSize=1)
    @Column(name = "id_salario_base", nullable = false)
    private Long idSalarioBase;

    private BigDecimal valorSalario;
    private Integer nivel;

    @CreatedDate
    LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "cargo_id_cargo")
    private Cargo cargo;

    public SalarioBase(SalarioBaseRequest salarioBaseRequest) {
        this.valorSalario = salarioBaseRequest.valorSalario();
        this.nivel = salarioBaseRequest.nivel();
    }

    public SalarioBase(SalarioBaseRequest request, Cargo cargo) {
        this.valorSalario = request.valorSalario();
        this.nivel = request.nivel();
        this.cargo = cargo;
    }
}

