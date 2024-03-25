package rlti.com.rh.calculo.response;

import rlti.com.rh.calculo.InssResult;
import rlti.com.rh.contrato.domain.Contrato;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

public record SimulacaoInssResponse(
        String nomeFuncionario,
        String cpf,
        String numeroMatricula,
        LocalDate dataAdmissao,
        String cargo,
        String setor,
        String mesCompetencia,
        BigDecimal salarioBruto,
        double aliquota,
        BigDecimal valorDescontoInss,
        BigDecimal fgts,
        BigDecimal salarioLiquido
) {
    public SimulacaoInssResponse(Contrato contrato, InssResult inssResult, YearMonth yearMonth) {
        this(
                contrato.getMatricula().getFuncionario().getNomeCompleto(),
                contrato.getMatricula().getFuncionario().getCpf(),
                contrato.getMatricula().getNumeroMatricula(),
                contrato.getDataAdmissao(),
                contrato.getCargo().getNomeCargo(),
                contrato.getSetor().getNomeSetor(),
                yearMonth.toString(),
                inssResult.getSalarioBruto(),
                inssResult.getAliquota(),
                inssResult.getInssCalculado(),
                inssResult.getSalarioBruto().multiply(BigDecimal.valueOf(0.08)), //fgts 8%
                inssResult.getValorLiquido()
        );
    }
}
