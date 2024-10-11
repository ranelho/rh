package com.rlti.rh.funcionario.application.api.response;

import com.rlti.rh.funcionario.domain.Formacao;

import java.time.Year;
import java.util.List;

public record FormacaoResponse(
        String curso,
        String instituicao,
        Year anoInicio,
        Year anoTermino,
        String situacao
) {
    public FormacaoResponse (Formacao formacao) {
        this(formacao.getCurso(), formacao.getInstituicao(), formacao.getAnoInicio(), formacao.getAnoTermino(), formacao.getSituacaoCurso().name());
    }

    public static List<FormacaoResponse> converte(List<Formacao> formacoes) {
        return formacoes
                .stream()
                .map(FormacaoResponse::new)
                .toList();
    }
}
