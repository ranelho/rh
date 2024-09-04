package com.rlti.rh.calculo.repository;

import com.rlti.rh.folha.domain.Vencimentos;
import com.rlti.rh.horas.domain.Horas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VencimentoRepositoryJpa extends JpaRepository<Vencimentos, Long> {

}
