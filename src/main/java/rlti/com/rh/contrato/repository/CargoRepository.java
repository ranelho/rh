package rlti.com.rh.contrato.repository;

import rlti.com.rh.contrato.domain.Cargo;

public interface CargoRepository {
    Cargo save(Cargo cargo);
    Cargo findByNomeCargo(String nomeCargo);
    Cargo findCargoById(Long idCargo);
}