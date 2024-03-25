package rlti.com.rh.funcionario.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import rlti.com.rh.funcionario.domain.Matricula;
import rlti.com.rh.funcionario.repository.MatriculaRepository;
import rlti.com.rh.handler.APIException;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MatriculaInfraRepository implements MatriculaRepository {
    private final MatriculaJpaRepository matriculaJpaRepository;

    @Override
    public Matricula findByNumeroMatricula(String matricula) {
        return matriculaJpaRepository.findByNumeroMatricula(matricula)
                .orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST,"Matricula não encontrada"));
    }

    @Override
    public Matricula novaMatricula(Matricula matricula) {
        return matriculaJpaRepository.save(matricula);
    }
}
