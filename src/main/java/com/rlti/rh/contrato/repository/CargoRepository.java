package com.rlti.rh.contrato.repository;

import com.rlti.rh.contrato.domain.Cargo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface CargoRepository {
    Cargo saveCargo(Cargo cargo);
    Cargo findByNomeCargo(String nomeCargo);
    Cargo findCargoById(Long idCargo);

    List<Cargo> findAllCargos();

    Optional<Cargo> findById(Long idCargo);
}
