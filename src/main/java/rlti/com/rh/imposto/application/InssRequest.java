package rlti.com.rh.imposto.application;

import java.time.LocalDate;

public record InssRequest(
        Double valorMinimo,
        Double valorMaximo,
        Double aliquota,
        LocalDate inicioVigencia,
        LocalDate fimVigencia,
        Double deducao
) {}