package rlti.com.rh.horas.application.api;

import java.util.Date;

public record HorasRequest(
        Integer diasTrabalhados,
        Integer faltas,
        Integer faltasJustificadas,
        Date mesReferencia,
        Float horasExtras,
        Float horasNoturnas,
        String numeroMatricula
) {
}
