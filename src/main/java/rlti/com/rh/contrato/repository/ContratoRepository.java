package rlti.com.rh.contrato.repository;

import rlti.com.rh.contrato.domain.Contrato;
import rlti.com.rh.funcionario.domain.Matricula;

public interface ContratoRepository {
    Contrato findContratoById(Long idContrato);
    Contrato saveContrato(Contrato contrato);

    Contrato findByMatricula(Matricula matricula);

}
