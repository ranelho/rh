package com.rlti.rh.funcionario.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import com.rlti.rh.funcionario.domain.Funcionario;
import com.rlti.rh.funcionario.repository.FuncionarioRepository;
import com.rlti.rh.handler.APIException;

import java.util.List;
import java.util.function.Supplier;

@Repository
@Slf4j
@RequiredArgsConstructor
public class FuncionarioInfraRepository implements FuncionarioRepository {
    private final FuncionarioJpaRepository funcionarioJpaRepository;
    private final ContatoJpaRepository contatoJpaRepository;

    @Override
    public Funcionario saveFuncionario(Funcionario funcionario) {
        try {
            contatoJpaRepository.save(funcionario.getContato());
            return funcionarioJpaRepository.save(funcionario);
        }catch (DataIntegrityViolationException e) {
            throw APIException.build(HttpStatus.BAD_REQUEST,"Funcionário já cadastrado, CPF: " + funcionario.getNomeCompleto());
        }
    }
    
    @Override
    public Funcionario findFuncionarioById(Long id) {
        return funcionarioJpaRepository.findById(id)
                .orElseThrow(getApiExceptionSupplier());
    }

    @Override
    public List<Funcionario> findAllFuncionariosByNome(String nome) {
        log.info("Buscando funcionario pelo nome: {}", nome);
        return funcionarioJpaRepository.findByNomeCompletoContainingIgnoreCase(nome.toUpperCase());
    }

    @Override
    public Funcionario findFuncionarioByMatricula(String matricula) {
        return funcionarioJpaRepository.findByNumeroMatricula(matricula).orElseThrow(getApiExceptionSupplier());
    }

    @Override
    public Funcionario findFuncionarioByCpf(String cpf) {
        return funcionarioJpaRepository.findByCpf(cpf).orElseThrow(getApiExceptionSupplier());
    }

    @Override
    public List<Funcionario> findAllFuncionarios() {
        return funcionarioJpaRepository.findAll();
    }

    @Override
    public List<Funcionario> findAllByAniversario(int mesAtual, int diaAtual) {
        return funcionarioJpaRepository.findAllByAniversario(mesAtual, diaAtual);
    }

    private static Supplier<APIException> getApiExceptionSupplier() {
        return () -> APIException.build(HttpStatus.BAD_REQUEST, "Funcionário não encontrado!");
    }
}
