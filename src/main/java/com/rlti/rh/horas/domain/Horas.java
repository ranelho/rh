package com.rlti.rh.horas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rlti.rh.contrato.domain.Contrato;
import com.rlti.rh.folha.domain.Vencimentos;
import com.rlti.rh.funcionario.domain.Matricula;
import com.rlti.rh.horas.application.api.HorasRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "HORAS")
public class Horas {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dependente_seq_generator")
    @SequenceGenerator(name="horas_trabalhadas_seq_generator", sequenceName = "horas_trabalhadas_sequence", allocationSize=1)
    @Column(name = "id_horas_trabalhadas", nullable = false)
    private Long idHorasTrabalhadas;

    private Integer diasTrabalhados;
    private Integer faltas;
    private Integer faltasJustificadas;
    private String mesCompetencia;
    private Double horasExtras;
    private Double horasNoturnas;
    private Boolean competenciaFechada;
    private BigDecimal salarioBase;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Vencimentos> vencimentos;

    @OneToOne
    @JoinColumn(name = "matricula_id_matricula")
    private Matricula matricula;

    @OneToOne
    @JoinColumn(name = "contrato_id_contrato")
    private Contrato contrato;

    public Horas(HorasRequest horasRequest, Matricula matricula, Contrato contrato, BigDecimal salarioBase, String mesCompetencia) {
        this.diasTrabalhados = horasRequest.getDiasTrabalhados();
        this.faltas = horasRequest.getFaltas();
        this.faltasJustificadas = horasRequest.getFaltasJustificadas();
        this.mesCompetencia = mesCompetencia;
        this.horasExtras = horasRequest.getHorasExtras();
        this.horasNoturnas = horasRequest.getHorasNoturnas();
        this.competenciaFechada = horasRequest.getCompetenciaFechada();
        this.matricula = matricula;
        this.contrato = contrato;
        this.salarioBase = salarioBase;
    }

    public void updateHoras(HorasRequest horasRequest) {
        this.diasTrabalhados = horasRequest.getDiasTrabalhados();
        this.faltas = horasRequest.getFaltas();
        this.faltasJustificadas = horasRequest.getFaltasJustificadas();
        this.horasExtras = horasRequest.getHorasExtras();
        this.horasNoturnas = horasRequest.getHorasNoturnas();
        this.competenciaFechada = horasRequest.getCompetenciaFechada();
    }
}
