package rlti.com.rh.funcionario.repository;

import rlti.com.rh.funcionario.domain.Cargo;

public interface CargoRepository {
    Cargo save(Cargo cargo);
    Cargo findByNomeCargo(String nomeCargo);
}
