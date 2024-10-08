package com.rlti.rh.funcionario.application.request;

import java.math.BigDecimal;

public record SalarioBaseRequest(
        Long idSalarioBase,
        BigDecimal valorSalario,
        Integer nivel
) {
}
