package com.rlti.rh.horas.application.api;

import com.rlti.rh.folha.application.api.VencimentosRequest;
import com.rlti.rh.horas.domain.Horas;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class HorasRequest {
    Integer diasTrabalhados;
    Integer faltas;
    Integer faltasJustificadas;
    Double horasExtras;
    Double horasNoturnas;
    Boolean competenciaFechada;
    List<VencimentosRequest> vencimentos;

    public HorasRequest(Horas horas) {
        this.diasTrabalhados = horas.getDiasTrabalhados();
        this.faltas = horas.getFaltas();
        this.faltasJustificadas = horas.getFaltasJustificadas();
        this.horasExtras = horas.getHorasExtras();
        this.horasNoturnas = horas.getHorasNoturnas();
        this.competenciaFechada = horas.getCompetenciaFechada();
        this.vencimentos = VencimentosRequest.converte(horas.getVencimentos());
    }
}
