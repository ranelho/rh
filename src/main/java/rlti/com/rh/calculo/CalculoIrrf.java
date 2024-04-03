package rlti.com.rh.calculo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import rlti.com.rh.imposto.doman.Irrf;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CalculoIrrf {

    public static IrResult calcularImpostoRenda(BigDecimal salarioComDescontoInss, List<Irrf> irrfList, int dependentes) {
        BigDecimal irrf = BigDecimal.ZERO;
        BigDecimal percentualDesconto = BigDecimal.ZERO;
        BigDecimal salarioBase = salarioComDescontoInss.subtract(BigDecimal.valueOf(dependentes * 189.59));

        for (Irrf irrfObj : irrfList) {
            if (salarioBase.compareTo(irrfObj.getValorMinimo()) >= 0 &&
                    (irrfObj.getValorMaximo() == null || salarioBase.compareTo(irrfObj.getValorMaximo()) <= 0)) {
                BigDecimal valorDesconto = salarioBase.multiply(BigDecimal.valueOf(irrfObj.getAliquota() / 100.0));
                valorDesconto = valorDesconto.subtract(irrfObj.getDeducao());
                irrf = irrf.add(valorDesconto); // Acumulando o valor do desconto
                percentualDesconto = BigDecimal.valueOf(irrfObj.getAliquota());
                break; // Já encontrou o intervalo de renda aplicável, então pode parar de procurar
            }
        }
        BigDecimal salarioLiquido = salarioComDescontoInss.subtract(irrf);

        return new IrResult(salarioComDescontoInss, dependentes, irrf, percentualDesconto, salarioLiquido);
    }
}
