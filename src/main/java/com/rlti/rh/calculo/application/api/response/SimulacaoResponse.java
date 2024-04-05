package com.rlti.rh.calculo.application.api.response;

import com.rlti.rh.calculo.InssResult;
import com.rlti.rh.calculo.IrResult;
import com.rlti.rh.contrato.domain.Contrato;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

public record SimulacaoResponse(
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
    public SimulacaoResponse(Contrato contrato, InssResult inssResult, IrResult irResult,
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
