package com.rlti.rh.folha.infra;

import com.rlti.rh.folha.domain.FolhaMensal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FolhaMensalJPARepository extends JpaRepository<FolhaMensal, Long> {
    Optional<FolhaMensal> findByNumeroMatriculaAndMesCompetencia(String numeroMatricula, String mesAno);
}
