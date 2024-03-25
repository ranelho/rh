package rlti.com.rh.calculo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rlti.com.rh.calculo.CalculoInss;
import rlti.com.rh.calculo.InssResult;
import rlti.com.rh.calculo.request.SimulacaoInssRequest;
import rlti.com.rh.calculo.response.SimulacaoInssResponse;
import rlti.com.rh.contrato.domain.Contrato;
import rlti.com.rh.contrato.repository.ContratoRepository;
import rlti.com.rh.funcionario.repository.MatriculaRepository;
import rlti.com.rh.imposto.doman.Inss;
import rlti.com.rh.imposto.repository.ImpostoRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalculoApplicationService implements CalculoService {

    private final ContratoRepository contratoRepository;
    private final ImpostoRepository impostoRepository;
    private final MatriculaRepository matriculaRepository;


    @Override
    public SimulacaoInssResponse simularCalculoInss(SimulacaoInssRequest request) {
        Contrato contrato = contratoRepository.findByMatricula(matriculaRepository.findByNumeroMatricula(request.matricula()));
        List<Inss> inss = impostoRepository.findVigencia(request.mesAno());
        BigDecimal salarioFuncionario = contrato.getCargo().getSalarioBase().getValorSalario();
        InssResult inssResult = CalculoInss.calcularINSS(salarioFuncionario, inss);
        return new SimulacaoInssResponse(contrato, inssResult, request.mesAno());
    }
}
