package rlti.com.rh.funcionario.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import rlti.com.rh.funcionario.domain.Funcionario;

public interface FuncionarioJpaRepository extends JpaRepository<Funcionario, Long> {
}
