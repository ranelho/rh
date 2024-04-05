package com.rlti.rh.imposto.application;

import com.rlti.rh.imposto.doman.Irrf;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record IrrfResponse(
        Double aliquota,
        BigDecimal deducao,
        LocalDate inicioVigencia,
        LocalDate fimVigencia,
        BigDecimal valorMinimo,
        BigDecimal valorMaximo
) {
    public IrrfResponse(Irrf irrf) {
        this(
                irrf.getAliquota(),
                irrf.getDeducao(),
                irrf.getInicioVigencia(),
                irrf.getFimVigencia(),
                irrf.getValorMinimo(),
                irrf.getValorMaximo()
        );
    }
    public static List<IrrfResponse> converte(List<Irrf> irrfs) {
        return irrfs.stream().map(IrrfResponse::new).toList();
    }
}
