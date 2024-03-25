package rlti.com.rh.contrato.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import rlti.com.rh.contrato.domain.Contrato;
import rlti.com.rh.contrato.repository.ContratoRepository;
import rlti.com.rh.funcionario.domain.Matricula;
import rlti.com.rh.handler.APIException;

import java.util.function.Supplier;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ContratoInfraRepository implements ContratoRepository {
    private final ContratoJpaRepository contratoJpaRepository;

    @Override
    public Contrato findContratoById(Long idContrato) {
        return contratoJpaRepository.findById(idContrato)
                .orElseThrow(getContratoNaoEncontrado());
    }

    @Override
    public Contrato criaContrato(Contrato contrato) {
        return contratoJpaRepository.save(contrato);
    }

    @Override
    public Contrato findByMatricula(Matricula matricula) {
        return contratoJpaRepository.findByMatricula(matricula)
                .orElseThrow(getContratoNaoEncontrado());
    }

    private static Supplier<APIException> getContratoNaoEncontrado() {
        return () -> APIException.build(HttpStatus.BAD_REQUEST, "Contrato não encontrado");
    }
}
