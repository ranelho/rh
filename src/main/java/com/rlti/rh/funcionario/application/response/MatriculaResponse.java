package com.rlti.rh.funcionario.application.response;

import com.rlti.rh.funcionario.domain.Matricula;

import java.util.List;

public record MatriculaResponse(
        String numeroMatricula
) {
    public MatriculaResponse(Matricula matricula) {
        this(matricula.getNumeroMatricula());
    }
    public static List<MatriculaResponse> converte(List<Matricula> matriculas) {
        return matriculas
                .stream()
                .map(MatriculaResponse::new)
                .toList();
    }
}
