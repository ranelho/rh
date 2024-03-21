package rlti.com.rh.funcionario.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import rlti.com.rh.funcionario.domain.Funcionario;

import java.util.List;
import java.util.Optional;

public interface FuncionarioJpaRepository extends JpaRepository<Funcionario, Long> {
    Optional<String> findByCpf(String cpf);
    List<Funcionario> findByNomeCompletoContainingIgnoreCase(String nome);
}
