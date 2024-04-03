package rlti.com.rh.funcionario.application.response;

import rlti.com.rh.funcionario.domain.Matricula;

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
