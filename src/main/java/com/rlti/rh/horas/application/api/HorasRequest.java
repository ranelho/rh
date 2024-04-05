package com.rlti.rh.horas.application.api;

public record HorasRequest(
        Integer diasTrabalhados,
        Integer faltas,
        Integer faltasJustificadas,
        String mesCompetencia,
        Double horasExtras,
        Double horasNoturnas,
        String numeroMatricula,
        Boolean competenciaFechada
) {
}
