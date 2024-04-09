package com.rlti.rh.contrato.application.response;

import com.rlti.rh.contrato.domain.Cargo;
import com.rlti.rh.funcionario.application.response.SalarioBaseResponse;

import java.util.List;

public record CargoResponse(
        Long idCargo,
        String nomeCargo,
        String grauDeInstrucao,
        String descricaoCargo,
        Integer quantidadeDeHorasSemanais,
        SalarioBaseResponse salarioBase
) {
    public CargoResponse(Cargo cargo) {
        this(
                cargo.getIdCargo(),
                cargo.getNomeCargo(),
                cargo.getGrauDeInstrucao().getDescricao(),
                cargo.getDescricaoCargo(),
                cargo.getQuantidadeDeHorasSemanais(),
                new SalarioBaseResponse(cargo.getSalarioBase()));
    }
    public static List<CargoResponse> of(List<Cargo> cargos) {
        return cargos.stream().map(CargoResponse::new).toList();
    }
}

