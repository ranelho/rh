package com.rlti.rh.funcionario.application.api.request;

import com.rlti.rh.funcionario.domain.enums.SituacaoCurso;
import jakarta.validation.constraints.NotNull;

import java.time.Year;

public record FormacaoRequest(
        @NotNull
        String curso,
        @NotNull
        String instituicao,
        Year anoInicio,
        Year anoTermino,
        SituacaoCurso situacaoCurso
) {
}
