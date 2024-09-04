package com.rlti.rh.funcionario.infra;

import com.rlti.rh.funcionario.domain.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MatriculaJpaRepository extends JpaRepository<Matricula, Long> {
    Optional<Matricula> findByNumeroMatricula(String matricula);

    @Query(value = "SELECT max(id_matricula) from matricula", nativeQuery = true)
    int findByLastIdMatricula();
}
