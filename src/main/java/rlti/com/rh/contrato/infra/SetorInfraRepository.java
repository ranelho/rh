package rlti.com.rh.contrato.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import rlti.com.rh.contrato.domain.Setor;
import rlti.com.rh.contrato.repository.SetorRepository;
import rlti.com.rh.handler.APIException;

@Repository
@Slf4j
@RequiredArgsConstructor
public class SetorInfraRepository implements SetorRepository {
    private final SetorJpaRepository setorJpaRepository;

    @Override
    public Setor findSetorById(Long idSetor) {
        return setorJpaRepository.findById(idSetor)
                .orElseThrow(()-> APIException.build(HttpStatus.BAD_REQUEST, "Setor não encontrado"));
    }

    @Override
    public Setor saveSetor(Setor setor) {
        return  setorJpaRepository.save(setor);
    }
}
