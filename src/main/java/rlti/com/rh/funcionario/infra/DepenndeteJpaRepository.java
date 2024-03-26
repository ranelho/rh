package rlti.com.rh.funcionario.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import rlti.com.rh.funcionario.domain.Dependente;
import rlti.com.rh.funcionario.domain.Funcionario;

public interface DepenndeteJpaRepository extends JpaRepository<Dependente, Long> {
    int countByFuncionario(Funcionario funcionario);
}
