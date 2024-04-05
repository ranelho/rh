package com.rlti.rh.folha.application.api;

import com.rlti.rh.calculo.InssResult;
import com.rlti.rh.calculo.IrResult;
import com.rlti.rh.contrato.domain.Contrato;
import com.rlti.rh.funcionario.domain.Funcionario;
import com.rlti.rh.horas.domain.HorasTrabalhadas;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record FolhaMensaRequest(
        Long idContrato,
        Long idFuncionario,
        String ctps,
        String pis,
  /*    String banco,
        String agencia,
        String conta,
        LocalDate dataPagamento,*/
        String nomeCompleto,
        String cpf,
        String numeroMatricula,
        LocalDate dataAdmissao,
        String cargo,
        String setor,
        String mesCompetencia,
        Integer quantidadeDependentes,
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
        BigDecimal totalDescontos
) {
    public FolhaMensaRequest(Contrato contrato, BigDecimal salarioBruto, IrResult irResult,
                             InssResult inssResult, Funcionario funcionario, String mesCompetencia,
                             int quantidadeDependentes, HorasTrabalhadas horasTrabalhadas,BigDecimal totalVencimentos,
                             BigDecimal totalDescontos) {
        this(
                contrato.getIdContrato(),
                funcionario.getIdFuncionario(),
                funcionario.getCtps(),
                funcionario.getPis(),
                funcionario.getNomeCompleto(),
                funcionario.getCpf(),
                contrato.getMatricula().getNumeroMatricula(),
                contrato.getDataAdmissao(),
                contrato.getCargo().getNomeCargo(),
                contrato.getSetor().getNomeSetor(),
                mesCompetencia,
                quantidadeDependentes,
                horasTrabalhadas.getDiasTrabalhados(),
                horasTrabalhadas.getHorasExtras(),
                horasTrabalhadas.getFaltas(),
                horasTrabalhadas.getHorasNoturnas(),
                salarioBruto,
                inssResult.getInssCalculado(),
                inssResult.getAliquota(),
                irResult.getIrrfCalculado(),
                irResult.getPercentualDesconto(),
                totalVencimentos.multiply(BigDecimal.valueOf(0.08)), //fgts 8%
                irResult.getSalarioLiquido(),
                totalVencimentos,
                totalDescontos
        );
    }
}
