package com.rlti.rh.folha;

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

    @Override
    public FolhaMensalResponse newFolha(FolhaMensaRequest folhaMensaRequest) {
         FolhaMensal folhaMensal = folhaMensalRepository.saveFolhaMensal(new FolhaMensal(folhaMensaRequest));
         return FolhaMensalResponse.builder().idFolhaMensal(folhaMensal.getIdFolhaMensal()).build();
    }
}
