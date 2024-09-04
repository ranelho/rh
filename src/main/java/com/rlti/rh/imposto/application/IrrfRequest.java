package com.rlti.rh.imposto.application;

import java.math.BigDecimal;
import java.time.LocalDate;

public record IrrfRequest (
        Double aliquota,
        LocalDate inicioVigencia,
        LocalDate fimVigencia,
        BigDecimal deducao,
        BigDecimal valorMaximo,
        BigDecimal valorMinimo
) {
}
