package rlti.com.rh.imposto.application;

import rlti.com.rh.imposto.doman.Inss;

import java.time.LocalDate;
import java.util.List;

public record InssResponse(
        Double valorMinimo,
        Double valorMaximo,
        Double aliquota,
        LocalDate inicioVigencia,
        LocalDate fimVigencia,
        Double deducao
) {
    public InssResponse(Inss inss) {
        this(inss.getValorMinimo(), inss.getValorMaximo(), inss.getAliquota(), inss.getInicioVigencia(),
                inss.getFimVigencia(), inss.getDeducao());
    }
    public static List<InssResponse> converte(List<Inss> insses) {
        return insses.stream().map(InssResponse::new).toList();
    }
}
