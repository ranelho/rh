package com.rlti.rh.funcionario.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rlti.rh.funcionario.domain.Dependente;
import com.rlti.rh.funcionario.domain.Funcionario;

import java.util.Optional;

public interface DepenndeteJpaRepository extends JpaRepository<Dependente, Long> {
    int countByFuncionario(Funcionario funcionario);

    Optional<Dependente> findByCpf(String cpfDependente);
}
