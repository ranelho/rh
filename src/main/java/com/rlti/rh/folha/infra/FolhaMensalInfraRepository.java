package com.rlti.rh.folha.infra;

import com.rlti.rh.folha.domain.FolhaMensal;
import com.rlti.rh.folha.repository.FolhaMensalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FolhaMensalInfraRepository implements FolhaMensalRepository {
    private final FolhaMensalJPARepository folhaMensalJPARepository;


    @Override
    public FolhaMensal saveFolhaMensal(FolhaMensal folhaMensal) {
        return folhaMensalJPARepository.save(folhaMensal);
    }
}
