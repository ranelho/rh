package rlti.com.rh.contrato.application.request;

import rlti.com.rh.funcionario.application.request.SalarioBaseRequest;
import rlti.com.rh.funcionario.domain.enums.GrauDeInstrucao;

public record CargoRequest(
        String nomeCargo,
        GrauDeInstrucao grauDeInstrucao,
        String descricaoCargo,
        Integer quantidadeDeHorasSemanais,
        SalarioBaseRequest salarioBase
) {
}
