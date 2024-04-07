package com.rlti.rh.folha.application.api;

import com.rlti.rh.calculo.service.FolhaMensalData;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record FolhaMensaRequest(
        Long idContrato,
        Long idFuncionario,
        String ctps,
        String pis,
        String nomeCompleto,
        String cpf,
        String numeroMatricula,
        LocalDate dataAdmissao,
        String cargo,
        String setor,
        String mesCompetencia,
        Integer diasTrabalhados,
        Double quantidadeHorasExtras,
        Integer quantidadeFaltas,
        Double quantidadeHorasNoturnas,
        BigDecimal salarioBruto,
        BigDecimal valorDescontoInss,
        Double aliquotaInss,
        BigDecimal valorDescontoIrrf,
        BigDecimal aliquotaIrrf,
        BigDecimal fgts,
        BigDecimal salarioLiquido,
        BigDecimal totalVencimentos,
        BigDecimal totalDescontos,
        String banco,
        String agencia,
        String conta
) {

    public FolhaMensaRequest(FolhaMensalData data) {
        this(
                data.contrato().getIdContrato(),
                data.contrato().getMatricula().getFuncionario().getIdFuncionario(),
                data.contrato().getMatricula().getFuncionario().getCtps(),
                data.contrato().getMatricula().getFuncionario().getPis(),
                data.contrato().getMatricula().getFuncionario().getNomeCompleto(),
                data.contrato().getMatricula().getFuncionario().getCpf(),
                data.contrato().getMatricula().getNumeroMatricula(),
                data.contrato().getDataAdmissao(),
                data.contrato().getCargo().getNomeCargo(),
                data.contrato().getSetor().getNomeSetor(),
                data.competencia(),
                data.horasTrabalhadas().getDiasTrabalhados(),
                data.horasTrabalhadas().getHorasExtras(),
                data.horasTrabalhadas().getFaltas(),
                data.horasTrabalhadas().getHorasNoturnas(),
                data.contrato().getCargo().getSalarioBase().getValorSalario(),
                data.inssResult().getInssCalculado(),
                data.inssResult().getAliquota(),
                data.irResult().getIrrfCalculado(),
                data.irResult().getPercentualDesconto(),
                data.totalVencimentos().multiply(BigDecimal.valueOf(0.08)),
                data.irResult().getSalarioLiquido(),
                data.totalVencimentos(),
                data.totalDescontos(),
                data.contrato().getMatricula().getFuncionario().getContaPagamento().getBanco(),
                data.contrato().getMatricula().getFuncionario().getContaPagamento().getAgencia(),
                data.contrato().getMatricula().getFuncionario().getContaPagamento().getNumeroConta()
        );
    }
}
