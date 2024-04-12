package com.rlti.rh.folha.repository;

import com.rlti.rh.folha.domain.FolhaMensal;

import java.util.Optional;

public interface FolhaMensalRepository {
    FolhaMensal saveFolhaMensal(FolhaMensal folhaMensal);
    Optional<FolhaMensal> findByMatriculaAndMesCompetencia(String numeroMatricula, String mesAno);
    Optional<FolhaMensal> findFolhaMensalByMatriculaAndMesCompetencia(String numeroMatricula, String mesAno, boolean aberta);
    void delete(FolhaMensal folhaMensal);
    Optional<FolhaMensal> findByMatriculaAndMesCompetenciaAndStatus(String numeroMatricula, String mesCompetencia, boolean b);
}
