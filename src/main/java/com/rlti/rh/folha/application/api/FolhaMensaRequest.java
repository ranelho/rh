package com.rlti.rh.folha.application.api;

import com.rlti.rh.calculo.service.FolhaMensalData;
import com.rlti.rh.folha.domain.TipoFolha;
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
        String conta,
        TipoFolha tipoFolha
) {

    public FolhaMensaRequest(FolhaMensalData data) {
        this(
                data.horas().getContrato().getIdContrato(),
                data.horas().getContrato().getMatricula().getFuncionario().getIdFuncionario(),
                data.horas().getContrato().getMatricula().getFuncionario().getCtps(),
                data.horas().getContrato().getMatricula().getFuncionario().getPis(),
                data.horas().getContrato().getMatricula().getFuncionario().getNomeCompleto(),
                data.horas().getContrato().getMatricula().getFuncionario().getCpf(),
                data.horas().getContrato().getMatricula().getNumeroMatricula(),
                data.horas().getContrato().getDataAdmissao(),
                data.horas().getContrato().getCargo().getNomeCargo(),
                data.horas().getContrato().getSetor().getNomeSetor(),
                data.horas().getMesCompetencia(),
                data.horas().getDiasTrabalhados(),
                data.horas().getHorasExtras(),
                data.horas().getFaltas(),
                data.horas().getHorasNoturnas(),
                data.horas().getSalarioBase(),
                data.inssResult().getInssCalculado(),
                data.inssResult().getAliquota(),
                data.irResult().getIrrfCalculado(),
                data.irResult().getPercentualDesconto(),
                data.totalVencimentos().multiply(BigDecimal.valueOf(0.08)),
                data.irResult().getSalarioLiquido(),
                data.totalVencimentos(),
                data.totalDescontos(),
                data.horas().getContrato().getMatricula().getFuncionario().getContaPagamento().getBanco(),
                data.horas().getContrato().getMatricula().getFuncionario().getContaPagamento().getAgencia(),
                data.horas().getContrato().getMatricula().getFuncionario().getContaPagamento().getNumeroConta(),
                TipoFolha.NORMAL
        );
    }
}
