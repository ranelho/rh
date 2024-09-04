package com.rlti.rh.contrato.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.rlti.rh.contrato.application.request.ContratoDesligamentoRequest;
import com.rlti.rh.contrato.application.request.ContratoRequest;
import com.rlti.rh.funcionario.domain.Matricula;

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
    @Column(name = "id_contrato", nullable = false)
    private Long idContrato;

    private LocalDate dataAdmissao;
    private LocalDate periodoAvaliacao;
    private LocalDateTime dataAssinaturaContrato;
    private LocalDate dataDesligamento;
    private String observacao;
    private Integer prazoTotalEmMeses;
    private LocalDate previsaoFimContrato;
    private Integer nivel;
    @Enumerated(EnumType.STRING)  private MotivoDesligamento motivoDesligamento;
    @Enumerated(EnumType.STRING)  private Prazo prazo;
    private Integer quantidadeValeTransporte;

    @OneToOne
    private Setor setor;

    @OneToOne
    @JoinColumn(name = "cargo_id_cargo")
    private Cargo cargo;

    @OneToOne
    @JoinColumn(name = "matricula_id_matricula")
    private Matricula matricula;

    @OneToOne
    @JoinColumn(name = "vale_transporte_id_vale_transporte")
    private AuxilioTransporte auxilioTransporte;

    public Contrato(Matricula matricula, Setor setor, Cargo cargo, ContratoRequest request) {
        this.matricula = matricula;
        this.setor = setor;
        this.cargo = cargo;
        this.dataAdmissao = request.dataAdmissao();
        this.periodoAvaliacao = request.dataAdmissao().plusDays(90);
        this.dataAssinaturaContrato = request.dataAssinaturaContrato();
        this.observacao = request.observacao();
        this.prazo  = request.prazo();
        if (request.prazo() == Prazo.DETERMINADO){
            this.prazoTotalEmMeses = request.prazoTotalEmMeses();
            this.previsaoFimContrato = request.dataAdmissao().plusMonths(request.prazoTotalEmMeses());
       }
        this.nivel = request.nivel();
    }

    public void desligamento(ContratoDesligamentoRequest desligamentoRequest) {
        this.dataDesligamento = desligamentoRequest.dataDesligamento();
        this.motivoDesligamento = desligamentoRequest.motivoDesligamento();
        this.observacao = desligamentoRequest.observacao();
    }

    public void renovacao(Integer prazoTotalEmMeses) {
        this.prazoTotalEmMeses += prazoTotalEmMeses;
        this.previsaoFimContrato =  this.previsaoFimContrato.plusMonths(prazoTotalEmMeses);
    }

    public void addValeTransporte(Integer quantidade, AuxilioTransporte auxilioTransporte) {
        this.quantidadeValeTransporte = quantidade;
        this.auxilioTransporte = auxilioTransporte;
    }
}
