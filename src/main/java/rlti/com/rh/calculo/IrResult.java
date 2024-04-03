package rlti.com.rh.calculo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class IrResult {
    private BigDecimal salarioComDescontoInss;
    private int dependentes;
    private BigDecimal irrfCalculado;
    private BigDecimal percentualDesconto;
    private BigDecimal salarioLiquido;
}
