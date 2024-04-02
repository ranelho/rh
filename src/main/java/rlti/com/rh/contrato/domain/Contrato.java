package rlti.com.rh.contrato.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rlti.com.rh.contrato.application.request.ContratoDesligamentoRequest;
import rlti.com.rh.contrato.application.request.ContratoRequest;
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
    private LocalDate periodoAvaliacao;
    private LocalDateTime dataAssinaturaContrato;
    private LocalDate dataDesligamento;
    @Enumerated(EnumType.STRING)  private MotivoDesligamento motivoDesligamento;
    private String observacao;
    @Enumerated(EnumType.STRING)  private Prazo prazo;
    private Integer prazoTotalEmMeses;
    LocalDate previsaoFimContrato;

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
        this.periodoAvaliacao = request.dataAdmissao().plusDays(90);
        this.dataAssinaturaContrato = request.dataAssinaturaContrato();
        this.observacao = request.observacao();
        if (request.prazo() == Prazo.DETERMINADO){
            this.prazo = request.prazo();
            this.prazoTotalEmMeses = request.prazoTotalEmMeses();
            this.previsaoFimContrato = request.dataAdmissao().plusMonths(request.prazoTotalEmMeses());
       }
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
}
