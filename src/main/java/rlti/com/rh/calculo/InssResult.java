package rlti.com.rh.calculo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class InssResult {
    private BigDecimal salarioBruto;
    private double aliquota;
    private BigDecimal inssCalculado;
    private BigDecimal valorLiquido;
}
