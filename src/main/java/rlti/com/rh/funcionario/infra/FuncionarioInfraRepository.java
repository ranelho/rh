package rlti.com.rh.funcionario.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import rlti.com.rh.funcionario.domain.Funcionario;
import rlti.com.rh.funcionario.repository.FuncionarioRepository;
import rlti.com.rh.handler.APIException;

import java.util.List;
import java.util.function.Supplier;

@Repository
@Slf4j
@RequiredArgsConstructor
public class FuncionarioInfraRepository implements FuncionarioRepository {
    private final FuncionarioJpaRepository funcionarioJpaRepository;

    @Override
    public Funcionario save(Funcionario funcionario) {
        try {
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
    public List<Funcionario> findFuncionariosByNome(String nome) {
        log.info("Buscando funcionario pelo nome: {}", nome);
        return funcionarioJpaRepository.findByNomeCompletoContainingIgnoreCase(nome.toUpperCase());
    }

    @Override
    public Funcionario findFuncionarioByMatricula(String matricula) {
        return funcionarioJpaRepository.findByNumeroMatricula(matricula).orElseThrow(getApiExceptionSupplier());
    }

    @Override
    public Funcionario findByCpf(String cpf) {
        return funcionarioJpaRepository.findByCpf(cpf).orElseThrow(getApiExceptionSupplier());
    }

    private static Supplier<APIException> getApiExceptionSupplier() {
        return () -> APIException.build(HttpStatus.BAD_REQUEST, "Funcionário não encontrado!");
    }
}
