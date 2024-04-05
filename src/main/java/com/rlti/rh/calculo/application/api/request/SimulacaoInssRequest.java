package com.rlti.rh.calculo.application.api.request;

import java.time.YearMonth;

public record SimulacaoInssRequest(
        YearMonth mesAno,
        String matricula
) {
}
