package com.rlti.rh.imposto.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.rlti.rh.imposto.application.InssRequest;
import com.rlti.rh.imposto.application.InssResponse;
import com.rlti.rh.imposto.application.IrrfRequest;
import com.rlti.rh.imposto.application.IrrfResponse;
import com.rlti.rh.imposto.doman.Inss;
import com.rlti.rh.imposto.doman.Irrf;
import com.rlti.rh.imposto.repository.ImpostoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImpostoApplicationService implements ImpostoService {

    private final ImpostoRepository impostoRepository;

    @Override
    public boolean criarInss(InssRequest inssRequest) {
        return impostoRepository.criasInss(new Inss(inssRequest));
    }

    @Override
    public boolean criarIrrf(IrrfRequest irrfRequest) {
        return  impostoRepository.criarIrrf(new Irrf(irrfRequest));
    }

    @Override
    public InssResponse consultarInss(Long id) {
        return new InssResponse(impostoRepository.consultarInss(id));
    }

    @Override
    public IrrfResponse consultarIrrf(Long id) {
        return new IrrfResponse(impostoRepository.consultarIrrf(id));
    }

    @Override
    public List<InssResponse> consultarAllInss(LocalDate inicioVigencia, LocalDate fimVigencia) {
        return InssResponse.converte(impostoRepository.consultarAllInss(inicioVigencia, fimVigencia));
    }
}
