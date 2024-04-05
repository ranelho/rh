package com.rlti.rh.folha.domain;


import com.rlti.rh.folha.application.api.FolhaMensaRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "FOLHA_MENSAL")
public class FolhaMensal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="folha_mensal_seq_generator", sequenceName = "folha_mensal_sequence", allocationSize=1)
    @Column(name = "id_folha_mensal", nullable = false)
    private Long idFolhaMensal;

    private Long idContrato;
    private Long idFuncionario;
    private String ctps;
    private String pis;
    private String banco;
    private String agencia;
    private String conta;
    private LocalDate dataPagamento;
    private String nomeFuncionario;
    private String cpf;
    private String numeroMatricula;
    private LocalDate dataAdmissao;
    private String cargo;
    private String setor;
    private String mesCompetencia;
    private Integer quantidadeDependentes;
    private Integer diasTrabalhados;
    private BigDecimal salarioBruto;
    private BigDecimal valorDescontoInss;
    private Double aliquotaInss;
    private BigDecimal valorDescontoIrrf;
    private Double aliquotaIrrf;
    private BigDecimal fgts;
    private BigDecimal totalVencimentos;
    private BigDecimal totalDescontos;
    private BigDecimal salarioLiquido;

    @OneToMany
    private List<Vencimentos> vencimentos;
    @OneToMany
    private List<Descontos> descontos;

    public FolhaMensal(FolhaMensaRequest folhaMensaRequest) {
        this.idContrato = folhaMensaRequest.idContrato();
        this.idFuncionario = folhaMensaRequest.idFuncionario();
        this.ctps = folhaMensaRequest.ctps();
        this.pis = folhaMensaRequest.pis();
/*        this.banco = folhaMensaRequest.banco();
        this.agencia = folhaMensaRequest.agencia();
        this.conta = folhaMensaRequest.conta();
        this.dataPagamento = folhaMensaRequest.dataPagamento();*/
        this.nomeFuncionario = folhaMensaRequest.nomeCompleto();
        this.cpf = folhaMensaRequest.cpf();
        this.numeroMatricula = folhaMensaRequest.numeroMatricula();
        this.dataAdmissao = folhaMensaRequest.dataAdmissao();
        this.cargo = folhaMensaRequest.cargo();
        this.setor = folhaMensaRequest.setor();
        this.mesCompetencia = folhaMensaRequest.mesCompetencia();
        this.quantidadeDependentes = folhaMensaRequest.quantidadeDependentes();
        this.diasTrabalhados = folhaMensaRequest.diasTrabalhados();
        this.salarioBruto = folhaMensaRequest.salarioBruto();
        this.valorDescontoInss = folhaMensaRequest.valorDescontoInss();
        this.aliquotaInss = folhaMensaRequest.aliquotaInss();
        this.valorDescontoIrrf = folhaMensaRequest.valorDescontoIrrf();
        this.fgts = folhaMensaRequest.fgts();
        this.salarioLiquido = folhaMensaRequest.salarioLiquido();
    }
}
