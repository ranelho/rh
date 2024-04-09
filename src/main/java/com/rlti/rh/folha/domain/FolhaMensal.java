package com.rlti.rh.folha.domain;


import com.rlti.rh.contrato.domain.Contrato;
import com.rlti.rh.empresa.domain.Empresa;
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
    @SequenceGenerator(name="folha_mensal_seq_generator", sequenceName = "folha_mensal_sequence", allocationSize=1 )
    @Column(name = "id_folha_mensal", nullable = false)
    private Long idFolhaMensal;

    private String cpf;
    private String mesCompetencia;
    @Enumerated(EnumType.STRING)
    private TipoFolha tipoFolha;
    private Long idContrato;
    private Long idFuncionario;
    private String ctps;
    private String pis;
    private String banco;
    private String agencia;
    private String conta;
    private LocalDate dataPagamento;
    private String nomeFuncionario;
    private String numeroMatricula;
    private LocalDate dataAdmissao;
    private String cargo;
    private String setor;
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
    private Boolean status;

    @OneToMany(mappedBy = "folhaMensal", cascade = CascadeType.ALL)
    private List<Vencimentos> vencimentos;

    @OneToMany(mappedBy = "folhaMensal", cascade = CascadeType.ALL)
    private List<Descontos> descontos;

    @OneToOne
    Empresa empresa;

    public FolhaMensal(FolhaMensaRequest folhaMensaRequest, Empresa empresa) {
        this.idContrato = folhaMensaRequest.idContrato();
        this.idFuncionario = folhaMensaRequest.idFuncionario();
        this.ctps = folhaMensaRequest.ctps();
        this.pis = folhaMensaRequest.pis();
        this.banco = folhaMensaRequest.banco();
        this.agencia = folhaMensaRequest.agencia();
        this.conta = folhaMensaRequest.conta();
        this.nomeFuncionario = folhaMensaRequest.nomeCompleto();
        this.cpf = folhaMensaRequest.cpf();
        this.numeroMatricula = folhaMensaRequest.numeroMatricula();
        this.dataAdmissao = folhaMensaRequest.dataAdmissao();
        this.cargo = folhaMensaRequest.cargo();
        this.setor = folhaMensaRequest.setor();
        this.mesCompetencia = folhaMensaRequest.mesCompetencia();
        this.diasTrabalhados = folhaMensaRequest.diasTrabalhados();
        this.salarioBruto = folhaMensaRequest.salarioBruto();
        this.valorDescontoInss = folhaMensaRequest.valorDescontoInss();
        this.aliquotaInss = folhaMensaRequest.aliquotaInss();
        this.valorDescontoIrrf = folhaMensaRequest.valorDescontoIrrf();
        this.fgts = folhaMensaRequest.fgts();
        this.salarioLiquido = folhaMensaRequest.salarioLiquido();
        this.totalVencimentos = folhaMensaRequest.totalVencimentos();
        this.totalDescontos = folhaMensaRequest.totalDescontos();
        this.status = false;
        this.empresa = empresa;
        this.tipoFolha = TipoFolha.NORMAL;
    }

    public void update(FolhaMensaRequest folhaMensaRequest) {
        this.diasTrabalhados = folhaMensaRequest.diasTrabalhados();
        this.salarioBruto = folhaMensaRequest.salarioBruto();
        this.valorDescontoInss = folhaMensaRequest.valorDescontoInss();
        this.aliquotaInss = folhaMensaRequest.aliquotaInss();
        this.valorDescontoIrrf = folhaMensaRequest.valorDescontoIrrf();
        this.fgts = folhaMensaRequest.fgts();
        this.salarioLiquido = folhaMensaRequest.salarioLiquido();
        this.totalVencimentos = folhaMensaRequest.totalVencimentos();
        this.totalDescontos = folhaMensaRequest.totalDescontos();
        this.status = false;
    }
}
