package com.rlti.rh.imposto.repository;

import com.rlti.rh.imposto.doman.Inss;
import com.rlti.rh.imposto.doman.Irrf;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface ImpostoRepository {
    boolean criasInss(Inss inss);
    boolean criarIrrf(Irrf irrf);
    Inss consultarInss(Long id);
    Irrf consultarIrrf(Long id);
    List<Inss> consultarAllInss(LocalDate inicioVigencia, LocalDate fimVigencia);
    List<Inss> findVigenciaInss(YearMonth yearMonth);
    List<Irrf> findVigenciaIrrf(YearMonth yearMonth);
}
