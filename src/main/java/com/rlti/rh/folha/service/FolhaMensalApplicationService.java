package com.rlti.rh.folha.service;

import com.rlti.rh.empresa.application.repository.EmpresaRepository;
import com.rlti.rh.empresa.domain.Empresa;
import com.rlti.rh.folha.application.api.FolhaMensaRequest;
import com.rlti.rh.folha.application.api.FolhaMensalResponse;
import com.rlti.rh.folha.domain.FolhaMensal;
import com.rlti.rh.folha.repository.FolhaMensalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FolhaMensalApplicationService implements FolhaMensalService {
    private final FolhaMensalRepository folhaMensalRepository;
    private final EmpresaRepository empresaRepository;

    @Override
    public FolhaMensalResponse newFolha(FolhaMensaRequest folhaMensaRequest) {
         Empresa empresa = empresaRepository.getByCnpj("81436017000198");
         FolhaMensal folhaMensal = folhaMensalRepository.saveFolhaMensal(new FolhaMensal(folhaMensaRequest, empresa));
         return FolhaMensalResponse.builder().idFolhaMensal(folhaMensal.getIdFolhaMensal()).build();
    }
}
