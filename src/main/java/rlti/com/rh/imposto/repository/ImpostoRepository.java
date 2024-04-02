package rlti.com.rh.imposto.repository;

import rlti.com.rh.imposto.doman.Inss;
import rlti.com.rh.imposto.doman.Irrf;

import java.time.LocalDate;
import java.util.List;

public interface ImpostoRepository {
    boolean criasInss(Inss inss);
    boolean criarIrrf(Irrf irrf);
    Inss consultarInss(Long id);
    Irrf consultarIrrf(Long id);
    List<Inss> consultarAllInss(LocalDate inicioVigencia, LocalDate fimVigencia);
}
