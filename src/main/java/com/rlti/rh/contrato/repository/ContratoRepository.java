package com.rlti.rh.contrato.repository;

import com.rlti.rh.contrato.domain.Contrato;
import com.rlti.rh.funcionario.domain.Matricula;

public interface ContratoRepository {
    Contrato findContratoById(Long idContrato);
    Contrato saveContrato(Contrato contrato);

    Contrato findByMatricula(Matricula matricula);

}
