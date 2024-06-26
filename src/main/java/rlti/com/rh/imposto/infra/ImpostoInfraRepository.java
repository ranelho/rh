package rlti.com.rh.imposto.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import rlti.com.rh.handler.APIException;
import rlti.com.rh.imposto.doman.Inss;
import rlti.com.rh.imposto.doman.Irrf;
import rlti.com.rh.imposto.repository.ImpostoRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ImpostoInfraRepository implements ImpostoRepository {

    private final InssJpaRepository inssJpaRepository;
    private final IrrfJpaRepository irrfJpaRepository;

    @Override
    public boolean criasInss(Inss inss) {
        log.info("ImpostoInfraRepository.criasInss");
        inssJpaRepository.save(inss);
        return true;
    }

    @Override
    public boolean criarIrrf(Irrf irrf) {
        log.info("ImpostoInfraRepository.criarIrrf");
        irrfJpaRepository.save(irrf);
        return true;
    }

    @Override
    public Inss consultarInss(Long id) {
        log.info("ImpostoInfraRepository.consultarInss");
        return inssJpaRepository.findById(id)
                .orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Inss não encontrado"));
    }

    @Override
    public Irrf consultarIrrf(Long id) {
        log.info("ImpostoInfraRepository.consultarIrrf");
        return irrfJpaRepository.findById(id)
                .orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Irrf não encontrado"));
    }

    @Override
    public List<Inss> consultarAllInss(LocalDate inicioVigencia, LocalDate fimVigencia) {
        log.info("ImpostoInfraRepository.consultarAllInss");
        return irrfJpaRepository.findByInicioVigenciaAndFimVigencia(inicioVigencia, fimVigencia);

    }
}
