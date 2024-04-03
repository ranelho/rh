package rlti.com.rh.funcionario.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import rlti.com.rh.funcionario.domain.Matricula;

import java.util.Optional;

public interface MatriculaJpaRepository extends JpaRepository<Matricula, Long>
{
    Optional<Matricula> findByNumeroMatricula(String matricula);
}
