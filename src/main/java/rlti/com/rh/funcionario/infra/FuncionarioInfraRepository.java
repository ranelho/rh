package rlti.com.rh.funcionario.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import rlti.com.rh.funcionario.domain.Funcionario;
import rlti.com.rh.funcionario.repository.FuncionarioRepository;
import rlti.com.rh.handler.APIException;

import java.util.Optional;

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
}
