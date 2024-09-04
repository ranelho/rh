package com.rlti.rh.contrato.repository;

import com.rlti.rh.contrato.domain.Cargo;

import java.util.Arrays;
import java.util.List;

public interface CargoRepository {
    Cargo saveCargo(Cargo cargo);
    Cargo findByNomeCargo(String nomeCargo);
    Cargo findCargoById(Long idCargo);

    List<Cargo> findAllCargos();
}
