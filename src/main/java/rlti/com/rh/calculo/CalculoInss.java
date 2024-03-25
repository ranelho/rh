package rlti.com.rh.calculo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import rlti.com.rh.imposto.doman.Inss;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CalculoInss {

    public static InssResult calcularINSS(BigDecimal salarioFuncionario, List<Inss> inssList) {
        BigDecimal inss = BigDecimal.ZERO;
        BigDecimal salarioBruto = salarioFuncionario;

        for (Inss inssObj : inssList) {
            if (salarioBruto.compareTo(BigDecimal.valueOf(inssObj.getValorMinimo())) >= 0 &&
                    (inssObj.getValorMaximo() == null || salarioBruto.compareTo(BigDecimal.valueOf(inssObj.getValorMaximo())) <= 0)) {
                // Verifica se o salário está dentro da faixa de aplicação da alíquota

                BigDecimal valorBase = salarioBruto;
                if (inssObj.getValorMaximo() != null && valorBase.compareTo(BigDecimal.valueOf(inssObj.getValorMaximo())) > 0) {
                    valorBase = BigDecimal.valueOf(inssObj.getValorMaximo());
                }

                BigDecimal valorDesconto = valorBase.multiply(BigDecimal.valueOf(inssObj.getAliquota() / 100.0));
                valorDesconto = valorDesconto.subtract(BigDecimal.valueOf(inssObj.getDeducao()));

                inss = valorDesconto;
                salarioBruto = salarioFuncionario.subtract(inss);

                break; // Já encontrou a alíquota aplicável, então pode parar de procurar
            }
        }

        return new InssResult(salarioFuncionario, inssList.get(0).getAliquota(), inss, salarioBruto);
    }

}
