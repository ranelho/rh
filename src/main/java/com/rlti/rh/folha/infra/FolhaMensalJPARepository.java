package com.rlti.rh.folha.infra;

import com.rlti.rh.folha.domain.FolhaMensal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolhaMensalJPARepository extends JpaRepository<FolhaMensal, Long> {
}
