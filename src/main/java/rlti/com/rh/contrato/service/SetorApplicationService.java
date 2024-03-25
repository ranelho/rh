package rlti.com.rh.contrato.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rlti.com.rh.contrato.application.api.request.SetorRequest;
import rlti.com.rh.contrato.application.api.response.SetorIdReponse;
import rlti.com.rh.contrato.domain.Setor;
import rlti.com.rh.contrato.repository.SetorRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class SetorApplicationService implements SetorService {
    private final SetorRepository setorRepository;

    @Override
    public SetorIdReponse criaSetor(SetorRequest request) {
        Setor setor = setorRepository.criaSetor(new Setor(request));
        return SetorIdReponse
                .builder()
                .idSetor(setor.getIdSetor())
                .build();
    }
}
