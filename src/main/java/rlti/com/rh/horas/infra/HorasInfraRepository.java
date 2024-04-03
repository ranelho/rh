package rlti.com.rh.horas.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import rlti.com.rh.funcionario.domain.Matricula;
import rlti.com.rh.handler.APIException;
import rlti.com.rh.horas.domain.HorasTrabalhadas;
import rlti.com.rh.horas.repository.HorasRepository;

import java.util.Date;

@Repository
@RequiredArgsConstructor
public class HorasInfraRepository implements HorasRepository {

    private final HorasJpaRepository horasJpaRepository;

    @Override
    public void salvarHoras(HorasTrabalhadas horasTrabalhadas) {
        horasJpaRepository.save(horasTrabalhadas);
    }

    @Override
    public HorasTrabalhadas findHorasByNumeroMatriculaAndMesReferencia(Matricula matricula, Date mesReferencia) {
        return  horasJpaRepository.findByMatriculaAndMesReferencia(matricula, mesReferencia)
                .orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Não localizado"));
    }
}
