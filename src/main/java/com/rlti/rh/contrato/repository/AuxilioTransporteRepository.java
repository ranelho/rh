package com.rlti.rh.contrato.repository;

import com.rlti.rh.contrato.domain.AuxilioTransporte;

import java.util.Arrays;
import java.util.List;

public interface AuxilioTransporteRepository {
    void saveAuxilioTransporte(AuxilioTransporte auxilioTransporte);
    AuxilioTransporte findById(Long idAuxilioTransporte);

    List<AuxilioTransporte> findAllAuxilioTransportes();
}
