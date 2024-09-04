package com.rlti.rh.contrato.application.request;

import com.rlti.rh.funcionario.application.request.SalarioBaseRequest;
import com.rlti.rh.funcionario.domain.enums.GrauDeInstrucao;

import java.util.List;

public record CargoRequest(
        String nomeCargo,
        GrauDeInstrucao grauDeInstrucao,
        Boolean exigeCursoSuperior,
        String descricaoCargo,
        Integer quantidadeDeHorasSemanais,
        List<SalarioBaseRequest> salarios
) {
}
