package rlti.com.rh.imposto.application;

import rlti.com.rh.imposto.doman.Irrf;

import java.time.LocalDate;
import java.util.List;

public record IrrfResponse(
        Double contribuicao,
        Double aliquota,
        LocalDate inicioVigencia,
        LocalDate fimVigencia,
        Double baseCalculo
) {
    public IrrfResponse(Irrf irrf) {
        this(irrf.getContribuicao(), irrf.getAliquota(), irrf.getInicioVigencia(), irrf.getFimVigencia(), irrf.getBaseCalculo());
    }
    public static List<IrrfResponse> converte(List<Irrf> irrfs) {
        return irrfs.stream().map(IrrfResponse::new).toList();
    }
}
