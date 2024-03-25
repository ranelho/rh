package rlti.com.rh.calculo.request;

import java.time.YearMonth;

public record SimulacaoInssRequest(
        YearMonth mesAno,
        String matricula
) {
}
