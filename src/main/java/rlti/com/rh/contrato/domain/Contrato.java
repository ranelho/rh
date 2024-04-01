package rlti.com.rh.contrato.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rlti.com.rh.contrato.application.api.ContratoRequest;
import rlti.com.rh.funcionario.domain.Matricula;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CONTRATO")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contato_seq_generator")
    @SequenceGenerator(name="contato_seq_generator", sequenceName = "contato_sequence", allocationSize=1)
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

    @OneToOne
    @JoinColumn(name = "matricula_id_matricula")
    private Matricula matricula;

    public Contrato(Matricula matricula, Setor setor, Cargo cargo, ContratoRequest request) {
        this.matricula = matricula;
        this.setor = setor;
        this.cargo = cargo;
        this.dataAdmissao = request.dataAdmissao();
        this.dataAssinaturaContrato = request.dataAssinaturaContrato();
        this.observacao = request.observacao();
    }
}
