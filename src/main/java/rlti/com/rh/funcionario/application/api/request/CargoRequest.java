package rlti.com.rh.funcionario.application.api.request;

import rlti.com.rh.funcionario.domain.GrauDeInstrucao;

public record CargoRequest(
        String nomeCargo,
        GrauDeInstrucao grauDeInstrucao,
        String descricaoCargo,
        Integer quantidadeDeHorasSemanais,
        SalarioBaseRequest salarioBase
) {
}
