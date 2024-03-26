package rlti.com.rh.calculo.application.api.response;

import rlti.com.rh.calculo.InssResult;
import rlti.com.rh.calculo.IrResult;
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
        int quantidadeDependentes,
        double aliquota,
        BigDecimal valorDescontoInss,
        BigDecimal valorDescontoIrrf,
        BigDecimal fgts,
        BigDecimal salarioLiquido
) {
    public SimulacaoInssResponse(Contrato contrato, InssResult inssResult, IrResult irResult,
                                 YearMonth yearMonth, int quantidadeDependentes) {
        this(
                contrato.getMatricula().getFuncionario().getNomeCompleto(),
                contrato.getMatricula().getFuncionario().getCpf(),
                contrato.getMatricula().getNumeroMatricula(),
                contrato.getDataAdmissao(),
                contrato.getCargo().getNomeCargo(),
                contrato.getSetor().getNomeSetor(),
                yearMonth.toString(),
                inssResult.getSalarioBruto(),
                quantidadeDependentes,
                inssResult.getAliquota(),
                inssResult.getInssCalculado(),
                irResult.getIrrfCalculado(),
                inssResult.getSalarioBruto().multiply(BigDecimal.valueOf(0.08)), //fgts 8%
                irResult.getSalarioLiquido()
        );
    }
}
