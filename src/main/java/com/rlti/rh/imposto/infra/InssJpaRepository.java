package com.rlti.rh.imposto.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.rlti.rh.imposto.doman.Inss;

import java.time.LocalDate;
import java.util.List;

public interface InssJpaRepository extends JpaRepository<Inss,Long> {
    List<Inss> findAllByInicioVigenciaAndFimVigencia(LocalDate inicioVigencia, LocalDate fimVigencia);

    @Query(value = "SELECT * FROM inss WHERE " +
            "inicio_vigencia <= to_date(?1 || '-01', 'YYYY-MM-DD') + interval '1 month' AND " +
            "fim_vigencia >= to_date(?1 || '-01', 'YYYY-MM-DD')", nativeQuery = true)
    List<Inss> findVigenciaInss(String yearMonth);
}
