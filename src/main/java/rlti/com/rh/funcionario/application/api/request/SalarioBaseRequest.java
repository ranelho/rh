package rlti.com.rh.funcionario.application.api.request;

import java.math.BigDecimal;

public record SalarioBaseRequest(
        Long idSalarioBase,
        BigDecimal valorSalario,
        Long nivel
) {
}
