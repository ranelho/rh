package com.rlti.rh.contrato.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import com.rlti.rh.contrato.domain.Contrato;
import com.rlti.rh.contrato.repository.ContratoRepository;
import com.rlti.rh.funcionario.domain.Matricula;
import com.rlti.rh.handler.APIException;

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
    public Contrato saveContrato(Contrato contrato) {
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
