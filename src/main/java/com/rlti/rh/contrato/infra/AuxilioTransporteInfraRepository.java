package com.rlti.rh.contrato.infra;

import com.rlti.rh.contrato.domain.AuxilioTransporte;
import com.rlti.rh.contrato.repository.AuxilioTransporteRepository;
import com.rlti.rh.handler.APIException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuxilioTransporteInfraRepository implements AuxilioTransporteRepository {
    private final AuxilioTransporteJpaRepository auxilioTransporteJpaRepository;

    @Override
    public void saveAuxilioTransporte(AuxilioTransporte auxilioTransporte) {
        auxilioTransporteJpaRepository.save(auxilioTransporte);
    }

    @Override
    public AuxilioTransporte findById(Long idAuxilioTransporte) {
        return auxilioTransporteJpaRepository.findById(idAuxilioTransporte).orElseThrow(
                () -> APIException.build(HttpStatus.BAD_REQUEST, "Auxilio de Transporte não encontrado")
        );
    }
}
