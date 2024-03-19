package rlti.com.rh.contrato.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rlti.com.rh.funcionario.domain.Cargo;
import rlti.com.rh.funcionario.domain.Funcionario;
import rlti.com.rh.funcionario.domain.Setor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CONTRATO")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_contato", nullable = false)
    private Long idContato;

    private LocalDate dataAdmissao;
    private LocalDateTime dataAssinaturaContrato;
    private LocalDate dataDesligamento;
    private MotivoDesligamento motivoDesligamento;
    private String observacao;

    @OneToOne
    private Setor setor;

    @OneToOne
    @JoinColumn(name = "cargo_id_cargo")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "funcionario_id_funcionario")
    private Funcionario funcionario;
}
