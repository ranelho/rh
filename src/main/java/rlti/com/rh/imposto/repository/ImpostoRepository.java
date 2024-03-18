package rlti.com.rh.imposto.repository;

import rlti.com.rh.imposto.application.IrrfResponse;
import rlti.com.rh.imposto.doman.Inss;
import rlti.com.rh.imposto.doman.Irrf;

import java.util.Optional;

public interface ImpostoRepository {
    boolean criasInss(Inss inss);
    boolean criarIrrf(Irrf irrf);
    Inss consultarInss(Long id);
    Irrf consultarIrrf(Long id);
}
