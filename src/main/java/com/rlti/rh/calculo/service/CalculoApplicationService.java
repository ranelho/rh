package com.rlti.rh.calculo.service;

import com.rlti.rh.AppConfig;
import com.rlti.rh.calculo.process.*;
import com.rlti.rh.calculo.application.api.request.SimulacaoInssRequest;
import com.rlti.rh.calculo.application.api.response.SimulacaoResponse;
import com.rlti.rh.calculo.repository.DescontosRepository;
import com.rlti.rh.calculo.repository.VencimentoRepository;
import com.rlti.rh.contrato.domain.Contrato;
import com.rlti.rh.contrato.repository.ContratoRepository;
import com.rlti.rh.empresa.application.repository.EmpresaRepository;
import com.rlti.rh.empresa.domain.Empresa;
import com.rlti.rh.folha.application.api.FolhaMensaRequest;
import com.rlti.rh.folha.domain.Descontos;
import com.rlti.rh.folha.domain.FolhaMensal;
import com.rlti.rh.folha.domain.Vencimentos;
import com.rlti.rh.folha.repository.FolhaMensalRepository;
import com.rlti.rh.funcionario.repository.DependenteRepository;
import com.rlti.rh.funcionario.repository.MatriculaRepository;
import com.rlti.rh.handler.APIException;
import com.rlti.rh.horas.domain.HorasTrabalhadas;
import com.rlti.rh.horas.repository.HorasRepository;
import com.rlti.rh.imposto.doman.Inss;
import com.rlti.rh.imposto.doman.Irrf;
import com.rlti.rh.imposto.repository.ImpostoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalculoApplicationService implements CalculoService {

    private final ContratoRepository contratoRepository;
    private final ImpostoRepository impostoRepository;
    private final MatriculaRepository matriculaRepository;
    private final DependenteRepository dependenteRepository;
    private final HorasRepository horasRepository;
    private final FolhaMensalRepository folhaRepository;
    private final DescontosRepository descontosRepository;
    private final EmpresaRepository empresaRepository;
    private final AppConfig appConfig;
    private final VencimentoRepository vencimentosRepository;

    @Override
    public SimulacaoResponse simularCalculoInss(SimulacaoInssRequest request) {
        Contrato contrato = contratoRepository.findByMatricula(matriculaRepository.findByNumeroMatricula(request.matricula()));
        List<Inss> inss = impostoRepository.findVigenciaInss(request.mesAno());
        List<Irrf> irrf = impostoRepository.findVigenciaIrrf(request.mesAno());
        int dependentes = dependenteRepository.countDependenteFuncionario(contrato.getMatricula().getFuncionario());
        BigDecimal salarioFuncionario = contrato.getCargo().getSalarioBase().getValorSalario();
        InssResult inssResult = CalculoInss.calcularINSS(salarioFuncionario, inss);
        IrResult irResult = CalculoIrrf.calcularImpostoRenda(inssResult.getValorLiquido(), irrf, dependentes);
        return new SimulacaoResponse(contrato, inssResult, irResult, request.mesAno(), dependentes);
    }

    @Override
    public void atualizarStatus(String mesCompetencia, String numeroMatricula, boolean status) {
        FolhaMensal folhaMensal = folhaRepository.findByMatriculaAndMesCompetencia(numeroMatricula, mesCompetencia)
                .orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Folha não encontrada"));
        HorasTrabalhadas horasTrabalhadas = horasRepository.findHorasByMesCompetenciaAndMatricula(mesCompetencia,
                matriculaRepository.findByNumeroMatricula(numeroMatricula));
        horasTrabalhadas.setCompetenciaFechada(status);
        folhaMensal.setStatus(status);
        horasRepository.salvarHoras(horasTrabalhadas);
        folhaRepository.saveFolhaMensal(folhaMensal);
    }

    @Override
    public boolean efetuarCalculo(String competencia) {
        List<HorasTrabalhadas> horasTrabalhadas = horasRepository.findAllHorasTrue(competencia);
        if (!horasTrabalhadas.isEmpty()) {
            YearMonth competenciaYearMonth = YearMonth.parse(competencia);
            List<Inss> inss = impostoRepository.findVigenciaInss(competenciaYearMonth);
            List<Irrf> irrf = impostoRepository.findVigenciaIrrf(competenciaYearMonth);
            horasTrabalhadas.parallelStream().forEach(horas ->
                    processarFolhaMensal(competencia, horas, inss, irrf)
            );
        }
        return true;
    }

    private void processarFolhaMensal(String competencia, HorasTrabalhadas horas, List<Inss> inss, List<Irrf> irrf) {
        Optional<FolhaMensal> optionalFolhaMensal = folhaRepository.findByMatriculaAndMesCompetencia(
                horas.getMatricula().getNumeroMatricula(), competencia);

        if (optionalFolhaMensal.isPresent() && Boolean.FALSE.equals(optionalFolhaMensal.get().getStatus())) {
            Contrato contrato = contratoRepository.findByMatricula(
                    matriculaRepository.findByNumeroMatricula(horas.getMatricula().getNumeroMatricula()));

            BigDecimal salarioFuncionario = contrato.getCargo().getSalarioBase().getValorSalario();
            int dependentes = dependenteRepository.countDependenteFuncionario(contrato.getMatricula().getFuncionario());
            InssResult inssResult = CalculoInss.calcularINSS(salarioFuncionario, inss);
            IrResult irResult = CalculoIrrf.calcularImpostoRenda(inssResult.getValorLiquido(), irrf, dependentes);

            BigDecimal horasExtras = BigDecimal.valueOf(0.0);
            if (horas.getHorasExtras()>0){
                horasExtras = CalculadoraHoraExtra.calcularValorHorasExtras(salarioFuncionario, horas.getHorasExtras(), horas.getHorasNoturnas());
            }
            BigDecimal totalVencimentos = salarioFuncionario.add(horasExtras);

            FolhaMensalData dados = new FolhaMensalData(competencia, horas, contrato, irResult, inssResult, totalVencimentos,
                    inssResult.getInssCalculado().add(irResult.getIrrfCalculado()));

            if (Boolean.FALSE.equals(optionalFolhaMensal.get().getStatus())) {
                folhaRepository.delete(optionalFolhaMensal.get());
            }
            FolhaMensal folhaMensal = saveFolhaMensal(dados);
            saveDescontos(inssResult, folhaMensal, irResult);
            saveVencimentos(folhaMensal, salarioFuncionario, horasExtras);
        }
    }

    private List<Vencimentos> getVencimentos(FolhaMensal folhaMensal, BigDecimal salarioFuncionario, BigDecimal valorExtras) {
        Vencimentos salario = new Vencimentos(salarioFuncionario, folhaMensal, "001", "SALARIO BASE");
        List<Vencimentos> vencimentosASalvar = new ArrayList<>();
        vencimentosASalvar.add(salario);
        if (valorExtras.compareTo(BigDecimal.ZERO) > 0) {
            Vencimentos extras = new Vencimentos(valorExtras, folhaMensal, "002", "HORA EXTRA");
            vencimentosASalvar.add(extras);
        }
        return vencimentosASalvar;
    }

    private static List<Descontos> getDescontos(InssResult inssResult, FolhaMensal folhaMensal, IrResult irResult) {
        Descontos descontoInss = new Descontos(inssResult, folhaMensal);
        List<Descontos> descontosASalvar = new ArrayList<>();
        if (irResult.getIrrfCalculado().compareTo(BigDecimal.ZERO) > 0) {
            Descontos descontoIrrf = new Descontos(irResult, folhaMensal);
            descontosASalvar.add(descontoIrrf);
        }
        descontosASalvar.add(descontoInss);
        return descontosASalvar;
    }

    private void saveVencimentos(FolhaMensal folhaMensal, BigDecimal salarioFuncionario, BigDecimal extras) {
        List<Vencimentos> vencimentosASalvar = getVencimentos(folhaMensal, salarioFuncionario, extras);
        folhaMensal.setVencimentos(vencimentosRepository.saveAll(vencimentosASalvar));
        folhaRepository.saveFolhaMensal(folhaMensal);
    }

    private void saveDescontos(InssResult inssResult, FolhaMensal folhaMensal, IrResult irResult) {
        List<Descontos> descontosASalvar = getDescontos(inssResult, folhaMensal, irResult);
        folhaMensal.setDescontos(descontosRepository.saveAll(descontosASalvar));
        folhaRepository.saveFolhaMensal(folhaMensal);
    }

    private FolhaMensal saveFolhaMensal(FolhaMensalData dados) {
        Empresa empresa = empresaRepository.getByCnpj(appConfig.getCnpj());
        FolhaMensaRequest folhaMensaRequest = new FolhaMensaRequest(dados);
        return folhaRepository.saveFolhaMensal(new FolhaMensal(folhaMensaRequest, empresa));
    }
}
