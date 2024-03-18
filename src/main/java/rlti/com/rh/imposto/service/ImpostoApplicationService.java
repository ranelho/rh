package rlti.com.rh.imposto.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rlti.com.rh.imposto.application.InssRequest;
import rlti.com.rh.imposto.application.InssResponse;
import rlti.com.rh.imposto.application.IrrfRequest;
import rlti.com.rh.imposto.application.IrrfResponse;
import rlti.com.rh.imposto.doman.Inss;
import rlti.com.rh.imposto.doman.Irrf;
import rlti.com.rh.imposto.repository.ImpostoRepository;

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
}
