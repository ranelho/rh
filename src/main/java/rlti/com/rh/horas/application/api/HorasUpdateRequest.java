package rlti.com.rh.horas.application.api;

public record HorasUpdateRequest(
        Integer diasTrabalhados,
        Integer faltas,
        Integer faltasJustificadas,
        Float horasExtras,
        Float horasNoturnas
) {
}
