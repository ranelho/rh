package com.rlti.rh.contrato.application.response;

import com.rlti.rh.contrato.domain.Cargo;
import com.rlti.rh.document.api.response.CargoDocumentResponse;

import java.util.List;

public record CargoDocResponse(
        String nomeCargo,
        List<CargoDocumentResponse> documents
) {
    public CargoDocResponse(Cargo cargo) {
        this(
                cargo.getNomeCargo(),
                CargoDocumentResponse.converter(cargo.getDocumentCargos())
                );
    }
    public static List<CargoDocResponse> convert(List<Cargo> cargos) {
        return cargos.stream().map(CargoDocResponse::new).toList();
    }
}

