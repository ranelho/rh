package com.rlti.rh.calculo.process;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class IrResult {
    private String codigo;
    private String descricao;
    private BigDecimal salarioComDescontoInss;
    private int dependentes;
    private BigDecimal irrfCalculado;
    private BigDecimal percentualDesconto;
    private BigDecimal salarioLiquido;
}
