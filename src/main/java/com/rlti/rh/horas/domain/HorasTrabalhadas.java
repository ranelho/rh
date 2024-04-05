package com.rlti.rh.horas.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.rlti.rh.funcionario.domain.Matricula;
import com.rlti.rh.horas.application.api.HorasRequest;
import com.rlti.rh.horas.application.api.HorasUpdateRequest;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "HORAS_TRABALHADAS")
public class HorasTrabalhadas {
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
    private Boolean aberto;

    @OneToOne
    @JoinColumn(name = "matricula_id_matricula")
    private Matricula matricula;

    public HorasTrabalhadas(HorasRequest horasRequest, Matricula matricula) {
        this.diasTrabalhados = horasRequest.diasTrabalhados();
        this.faltas = horasRequest.faltas();
        this.faltasJustificadas = horasRequest.faltasJustificadas();
        this.mesCompetencia = horasRequest.mesCompetencia();
        this.horasExtras = horasRequest.horasExtras();
        this.horasNoturnas = horasRequest.horasNoturnas();
        this.aberto = horasRequest.aberto();
        this.matricula = matricula;
    }

    public void updateHoras(HorasUpdateRequest horasRequest) {
        this.diasTrabalhados = horasRequest.diasTrabalhados();
        this.faltas = horasRequest.faltas();
        this.faltasJustificadas = horasRequest.faltasJustificadas();
        this.horasExtras = horasRequest.horasExtras();
        this.horasNoturnas = horasRequest.horasNoturnas();
        this.aberto = horasRequest.aberto();
    }
}
