package rlti.com.rh.folha.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

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
    private String nomeFuncionario;
    private String cpf;
    private String numeroMatricula;
    private LocalDate dataAdmissao;
    private String cargo;
    private String setor;
    private String mesCompetencia;
    private Integer quantidadeDependentes;
    private Integer diasTrabalhados;
    private Integer quantidadeHorasExtras;
    private Integer quantidadeFaltas;
    private Integer quantidadeHorasNoturnas;
    private Double aliquota;
    private BigDecimal valorHoraExtra;
    private BigDecimal valorHoraNoturna;
    private BigDecimal salarioBruto;
    private BigDecimal valorDescontoInss;
    private BigDecimal valorDescontoIrrf;
    private BigDecimal fgts;
    private BigDecimal salarioLiquido;

}
