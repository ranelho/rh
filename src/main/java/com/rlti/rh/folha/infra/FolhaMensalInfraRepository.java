package com.rlti.rh.folha.infra;

import com.rlti.rh.folha.domain.FolhaMensal;
import com.rlti.rh.folha.repository.FolhaMensalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FolhaMensalInfraRepository implements FolhaMensalRepository {
    private final FolhaMensalJPARepository folhaMensalJPARepository;


    @Override
    public FolhaMensal saveFolhaMensal(FolhaMensal folhaMensal) {
        return folhaMensalJPARepository.save(folhaMensal);
    }

    @Override
    public Optional<FolhaMensal> findByMatriculaAndMesCompetencia(String numeroMatricula, String mesAno) {
        return folhaMensalJPARepository.findByNumeroMatriculaAndMesCompetencia(numeroMatricula, mesAno);
    }

    @Override
    public Optional<FolhaMensal> findFolhaMensalByMatriculaAndMesCompetencia(String numeroMatricula, String mesAno, boolean fechada) {
        return folhaMensalJPARepository.findByNumeroMatriculaAndMesCompetenciaAndStatus(numeroMatricula, mesAno, fechada);
    }

    @Override
    public void delete(FolhaMensal folhaMensal) {
        folhaMensalJPARepository.delete(folhaMensal);
    }

    @Override
    public Optional<FolhaMensal> findByMatriculaAndMesCompetenciaAndStatus(String numeroMatricula, String mesCompetencia, boolean status) {
        return folhaMensalJPARepository.findByNumeroMatriculaAndMesCompetenciaAndStatus(numeroMatricula, mesCompetencia, status);
    }
}
