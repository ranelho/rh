package com.rlti.rh.horas.application.api;

public record HorasUpdateRequest(
        Integer diasTrabalhados,
        Integer faltas,
        Integer faltasJustificadas,
        Double horasExtras,
        Double horasNoturnas,
        String numeroMatricula,
        Boolean competenciaFechada
) {
}
