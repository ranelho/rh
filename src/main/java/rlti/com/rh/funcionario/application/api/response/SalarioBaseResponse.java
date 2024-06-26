package rlti.com.rh.funcionario.application.api.response;

import rlti.com.rh.funcionario.domain.SalarioBase;

import java.math.BigDecimal;
import java.util.List;

public record SalarioBaseResponse(
        Long idSalarioBase,
        BigDecimal valorSalarioBase,
        Long nivel
) {
    public SalarioBaseResponse(SalarioBase salarioBase) {
        this(
                salarioBase.getIdSalarioBase(),
                salarioBase.getValorSalario(),
                salarioBase.getNivel()
        );
    }
    public static List<SalarioBaseResponse> converter(List<SalarioBase> salarioBases) {
        return salarioBases.stream().map(SalarioBaseResponse::new).toList();
    }
}
