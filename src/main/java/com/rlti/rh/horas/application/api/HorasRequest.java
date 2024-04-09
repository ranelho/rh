package com.rlti.rh.horas.application.api;

import com.rlti.rh.folha.application.api.VencimentosRequest;

import java.util.List;

public record HorasRequest(
        Integer diasTrabalhados,
        Integer faltas,
        Integer faltasJustificadas,
        String mesCompetencia,
        Double horasExtras,
        Double horasNoturnas,
        String numeroMatricula,
        Boolean competenciaFechada,
        List<VencimentosRequest> vencimentos
) {
}
