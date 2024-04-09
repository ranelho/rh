package com.rlti.rh.calculo.infra;

import com.rlti.rh.folha.domain.Descontos;
import com.rlti.rh.folha.domain.FolhaMensal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescontosJpaRepository extends JpaRepository<Descontos, Long> {
    void deleteAllByFolhaMensal(FolhaMensal folhaMensal);
}
