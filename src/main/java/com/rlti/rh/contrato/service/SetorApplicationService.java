package com.rlti.rh.contrato.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.rlti.rh.contrato.application.request.SetorRequest;
import com.rlti.rh.contrato.application.response.SetorIdReponse;
import com.rlti.rh.contrato.domain.Setor;
import com.rlti.rh.contrato.repository.SetorRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class SetorApplicationService implements SetorService {
    private final SetorRepository setorRepository;

    @Override
    public SetorIdReponse newSetor(SetorRequest request) {
        Setor setor = setorRepository.saveSetor(new Setor(request));
        return SetorIdReponse.builder().idSetor(setor.getIdSetor()).build();
    }
}
