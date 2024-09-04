package com.rlti.rh.calculo.process;

import com.rlti.rh.folha.domain.Vencimentos;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import com.rlti.rh.imposto.doman.Inss;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CalculoInss {

    public static InssResult calcularINSS(List<Vencimentos> vencimentos, List<Inss> inssList) {
        BigDecimal salarioBruto = BigDecimal.ZERO;
        BigDecimal inss = BigDecimal.ZERO;
        BigDecimal valorLiquido = BigDecimal.ZERO;
        double aliquota = 0;

        // Calcular o total do salário bruto e dos vencimentos
        BigDecimal totalVencimentos = BigDecimal.ZERO;
        for (Vencimentos vencimento : vencimentos) {
            if (Boolean.TRUE.equals(vencimento.getDedutivel())) {
                salarioBruto = salarioBruto.add(vencimento.getValorVencimento());
            }
            totalVencimentos = totalVencimentos.add(vencimento.getValorVencimento());
        }

        // Iterar sobre as faixas de INSS
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
                valorLiquido = salarioBruto.subtract(inss);
                aliquota = inssObj.getAliquota();

                break;
            }
        }
        return new InssResult(totalVencimentos, aliquota, inss, valorLiquido);
    }

}
