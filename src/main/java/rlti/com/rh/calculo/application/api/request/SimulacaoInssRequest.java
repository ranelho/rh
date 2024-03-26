package rlti.com.rh.calculo.application.api.request;

import java.time.YearMonth;

public record SimulacaoInssRequest(
        YearMonth mesAno,
        String matricula
) {
}
