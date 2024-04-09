package com.rlti.rh.calculo.service;

import com.rlti.rh.AppConfig;
import com.rlti.rh.calculo.process.*;
import com.rlti.rh.calculo.application.api.request.SimulacaoInssRequest;
import com.rlti.rh.calculo.application.api.response.SimulacaoResponse;
import com.rlti.rh.calculo.repository.DescontosRepository;
import com.rlti.rh.calculo.repository.VencimentoRepository;
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
import com.rlti.rh.horas.domain.Horas;
import com.rlti.rh.horas.repository.CodigoRepository;
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
    private final CodigoRepository codigoRepository;

    @Override
    public SimulacaoResponse simularCalculoInss(SimulacaoInssRequest request) {
       /* Contrato contrato = contratoRepository.findByMatricula(matriculaRepository.findByNumeroMatricula(request.matricula()));
        List<Inss> inss = impostoRepository.findVigenciaInss(request.mesAno());
        List<Irrf> irrf = impostoRepository.findVigenciaIrrf(request.mesAno());
        int dependentes = dependenteRepository.countDependenteFuncionario(contrato.getMatricula().getFuncionario());
        BigDecimal salarioFuncionario = contrato.getCargo().getSalarioBase().getValorSalario();
        InssResult inssResult = CalculoInss.calcularINSS(salarioFuncionario, inss);
        IrResult irResult = CalculoIrrf.calcularImpostoRenda(inssResult.getValorLiquido(), irrf, dependentes);
        return new SimulacaoResponse(contrato, inssResult, irResult, request.mesAno(), dependentes);*/
        return null;
    }

    @Override
    public void atualizarStatus(String mesCompetencia, String numeroMatricula, boolean status) {
        FolhaMensal folhaMensal = folhaRepository.findByMatriculaAndMesCompetencia(numeroMatricula, mesCompetencia)
                .orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Folha não encontrada"));
        Horas horas = horasRepository.findHorasByMesCompetenciaAndMatricula(mesCompetencia,
                matriculaRepository.findByNumeroMatricula(numeroMatricula));
        horas.setCompetenciaFechada(status);
        folhaMensal.setStatus(status);
        horasRepository.salvarHoras(horas);
        folhaRepository.saveFolhaMensal(folhaMensal);
    }

    @Override
    public boolean efetuarCalculo(String competencia) {
        List<Horas> horasTrabalhadas = horasRepository.findAllHorasTrue(competencia);
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

    private void processarFolhaMensal(String competencia, Horas horas, List<Inss> inss, List<Irrf> irrf) {
        //pegar vencimentos
        Optional<FolhaMensal> optionalFolhaMensal = folhaRepository.findByMatriculaAndMesCompetencia(
                horas.getMatricula().getNumeroMatricula(), competencia);

        if (optionalFolhaMensal.isEmpty() || Boolean.FALSE.equals(optionalFolhaMensal.get().getStatus())) {
            int dependentes = dependenteRepository.countDependenteFuncionario(horas.getContrato().getMatricula().getFuncionario());
            //calcular inss
            InssResult inssResult = CalculoInss.calcularINSS(horas.getVencimentos(), inss);
            //calcular irrf
            IrResult irResult = CalculoIrrf.calcularImpostoRenda(inssResult.getValorLiquido(), irrf, dependentes);
            //calcular total vencimentos
            BigDecimal totalVencimentos = inssResult.getTotalVencimentos();
            //calcular total descontos
            BigDecimal totalDescontos = inssResult.getInssCalculado().add(irResult.getIrrfCalculado());
            //salvar folha
            FolhaMensalData dados = new FolhaMensalData(horas, irResult, inssResult, totalVencimentos, totalDescontos);

            if (optionalFolhaMensal.isPresent() && Boolean.FALSE.equals(optionalFolhaMensal.get().getStatus())) {
                folhaRepository.delete(optionalFolhaMensal.get());
            }
            FolhaMensal folhaMensal = saveFolhaMensal(dados);
            saveDescontos(inssResult, folhaMensal, irResult);
            saveVencimentos(horas.getVencimentos(), folhaMensal);
        }
    }

    private void saveVencimentos(List<Vencimentos> vencimentos, FolhaMensal folhaMensal) {
        vencimentos.forEach(vencimento -> vencimento.setFolhaMensal(folhaMensal));
        vencimentosRepository.saveAll(vencimentos);
    }

    private void saveDescontos(InssResult inssResult, FolhaMensal folhaMensal, IrResult irResult) {
        List<Descontos> descontosASalvar = getDescontos(inssResult, folhaMensal, irResult);
        folhaMensal.setDescontos(descontosRepository.saveAll(descontosASalvar));
        folhaRepository.saveFolhaMensal(folhaMensal);
    }

    private List<Descontos> getDescontos(InssResult inssResult, FolhaMensal folhaMensal, IrResult irResult) {
        Descontos descontoInss = new Descontos(inssResult, folhaMensal, codigoRepository.findByCodigo("003"));
        List<Descontos> descontosASalvar = new ArrayList<>();
        if (irResult.getIrrfCalculado().compareTo(BigDecimal.ZERO) > 0) {
            Descontos descontoIrrf = new Descontos(irResult, folhaMensal, codigoRepository.findByCodigo("004"));
            descontosASalvar.add(descontoIrrf);
        }
        descontosASalvar.add(descontoInss);
        return descontosASalvar;
    }

    private FolhaMensal saveFolhaMensal(FolhaMensalData dados) {
        Empresa empresa = empresaRepository.getByCnpj(appConfig.getCnpj());
        FolhaMensaRequest folhaMensaRequest = new FolhaMensaRequest(dados);
        return folhaRepository.saveFolhaMensal(new FolhaMensal(folhaMensaRequest, empresa));
    }
}
