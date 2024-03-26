package rlti.com.rh.horas.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rlti.com.rh.funcionario.domain.Matricula;
import rlti.com.rh.horas.application.api.HorasRequest;
import rlti.com.rh.horas.application.api.HorasUpdateRequest;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "HORAS_TRABALHADAS")
public class HorasTrabalhadas {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_horas_trabalhadas", nullable = false)
    private Long idHorasTrabalhadas;

    private Integer diasTrabalhados;
    private Integer faltas;
    private Integer faltasJustificadas;
    private Date mesReferencia;
    private Float horasExtras;
    private Float horasNoturnas;
    private Boolean aberto;

    @OneToOne
    @JoinColumn(name = "matricula_id_matricula")
    private Matricula matricula;

    public HorasTrabalhadas(HorasRequest horasRequest, Matricula matricula) {
        this.diasTrabalhados = horasRequest.diasTrabalhados();
        this.faltas = horasRequest.faltas();
        this.faltasJustificadas = horasRequest.faltasJustificadas();
        this.mesReferencia = horasRequest.mesReferencia();
        this.horasExtras = horasRequest.horasExtras();
        this.horasNoturnas = horasRequest.horasNoturnas();
        this.matricula = matricula;
    }

    public void updateHoras(HorasUpdateRequest horasRequest) {
        this.diasTrabalhados = horasRequest.diasTrabalhados();
        this.horasNoturnas = horasRequest.horasNoturnas();
        this.faltas = horasRequest.faltas();
        this.faltasJustificadas = horasRequest.faltasJustificadas();
        this.horasExtras = horasRequest.horasExtras();
    }
}
