package rlti.com.rh.imposto.application;

import java.time.LocalDate;

public record IrrfRequest (
        Double contribuicao,
        Double aliquota,
        LocalDate inicioVigencia,
        LocalDate fimVigencia,
        Double baseCalculo
) {
}
