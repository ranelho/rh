package rlti.com.rh.contrato.application.api.request;

import rlti.com.rh.funcionario.application.api.request.SalarioBaseRequest;
import rlti.com.rh.funcionario.domain.enums.GrauDeInstrucao;

public record CargoRequest(
        String nomeCargo,
        GrauDeInstrucao grauDeInstrucao,
        String descricaoCargo,
        Integer quantidadeDeHorasSemanais,
        SalarioBaseRequest salarioBase
) {
}
