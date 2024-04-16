package com.rlti.rh.contrato.repository;

import com.rlti.rh.contrato.domain.AuxilioTransporte;

public interface AuxilioTransporteRepository {
    void saveAuxilioTransporte(AuxilioTransporte auxilioTransporte);
    AuxilioTransporte findById(Long idAuxilioTransporte);
}
