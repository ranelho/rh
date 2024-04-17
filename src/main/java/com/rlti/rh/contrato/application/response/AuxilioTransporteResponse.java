package com.rlti.rh.contrato.application.response;

import com.rlti.rh.contrato.domain.AuxilioTransporte;

public record AuxilioTransporteResponse(
        Long idAuxilioTransporte,
        Double valorUnitario,
        String descricao
) {
    public AuxilioTransporteResponse (AuxilioTransporte auxilioTransporte){
        this(auxilioTransporte.getIdAuxilioTransporte(), auxilioTransporte.getValorUnitario(), auxilioTransporte.getDescricao());
    }

}
