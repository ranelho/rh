package com.rlti.rh.funcionario.infra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.rlti.rh.funcionario.domain.Funcionario;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

public interface FuncionarioJpaRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByCpf(String cpf);

    @Query(value = "select f.* from funcionario F inner join matricula m on M.funcionario_id_funcionario = f.id_funcionario \n" +
            "\twhere m.numero_matricula  = :numeroMatricula", nativeQuery = true)
    Optional<Funcionario> findByNumeroMatricula(String numeroMatricula);

    @Query(value = "SELECT * FROM Funcionario WHERE EXTRACT(MONTH FROM data_nascimento) = :mesAtual AND EXTRACT(DAY FROM data_nascimento) = :diaAtual", nativeQuery = true)
    List<Funcionario> findAllByAniversario(int mesAtual, int diaAtual);

    Page<Funcionario> findByNomeCompletoContainingIgnoreCase(String nome, Pageable pageableWithFixedSort);
}
