package rlti.com.rh.funcionario.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rlti.com.rh.funcionario.domain.Funcionario;

import java.util.List;
import java.util.Optional;

public interface FuncionarioJpaRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByCpf(String cpf);
    List<Funcionario> findByNomeCompletoContainingIgnoreCase(String nome);

    @Query(value = "select f.* from funcionario F inner join matricula m on M.funcionario_id_funcionario = f.id_funcionario \n" +
            "\twhere m.numero_matricula  = :numeroMatricula", nativeQuery = true)
    Optional<Funcionario> findByNumeroMatricula(String numeroMatricula);
}
