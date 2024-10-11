package com.rlti.rh.contrato.application.response;

import com.rlti.rh.contrato.domain.Cargo;
import com.rlti.rh.document.api.response.CargoDocumentResponse;
import com.rlti.rh.funcionario.application.api.response.SalarioBaseResponse;

import java.util.List;

public record CargoResponse(
        Long idCargo,
        String nomeCargo,
        String grauDeInstrucao,
        String descricaoCargo,
        Integer quantidadeDeHorasSemanais,
        List<SalarioBaseResponse> salarios,
        List<CargoDocumentResponse> documents
) {
    public CargoResponse(Cargo cargo) {
        this(
                cargo.getIdCargo(),
                cargo.getNomeCargo(),
                cargo.getGrauDeInstrucao().getDescricao(),
                cargo.getDescricaoCargo(),
                cargo.getQuantidadeDeHorasSemanais(),
                SalarioBaseResponse.converter(cargo.getSalarios()),
                CargoDocumentResponse.converter(cargo.getDocumentCargos())
                );
    }
    public static List<CargoResponse> convert(List<Cargo> cargos) {
        return cargos.stream().map(CargoResponse::new).toList();
    }
}

