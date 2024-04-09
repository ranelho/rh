package com.rlti.rh.funcionario.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rlti.rh.funcionario.domain.Matricula;

import java.util.Optional;

public interface MatriculaJpaRepository extends JpaRepository<Matricula, Long>
{
    Optional<Matricula> findByNumeroMatricula(String matricula);
}
