package com.rlti.rh.contrato.repository;

import com.rlti.rh.contrato.domain.Cargo;

public interface CargoRepository {
    Cargo saveCargo(Cargo cargo);
    Cargo findByNomeCargo(String nomeCargo);
    Cargo findCargoById(Long idCargo);
}
