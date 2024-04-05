package com.rlti.rh.folha.infra;

import com.rlti.rh.folha.domain.FolhaMensal;
import com.rlti.rh.folha.repository.FolhaMensalRepository;
import com.rlti.rh.handler.APIException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FolhaMensalInfraRepository implements FolhaMensalRepository {
    private final FolhaMensalJPARepository folhaMensalJPARepository;


    @Override
    public FolhaMensal saveFolhaMensal(FolhaMensal folhaMensal) {
        return folhaMensalJPARepository.save(folhaMensal);
    }

    @Override
    public FolhaMensal findByMatriculaAndMesCompetencia(String numeroMatricula, String mesAno) {
        return folhaMensalJPARepository.findByNumeroMatriculaAndMesCompetencia(numeroMatricula, mesAno)
                .orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Contracheque não localizado"));
    }
}
