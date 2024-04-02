package rlti.com.rh.funcionario.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import rlti.com.rh.funcionario.domain.Dependente;
import rlti.com.rh.funcionario.domain.Funcionario;
import rlti.com.rh.funcionario.repository.DependenteRepository;
import rlti.com.rh.handler.APIException;

@Repository
@Slf4j
@RequiredArgsConstructor
public class DependenteInfraRepository implements DependenteRepository {

    private final DepenndeteJpaRepository dependenteJpaRepository;

    @Override
    public int countDependenteFuncionario(Funcionario funcionario) {
        return dependenteJpaRepository.countByFuncionario(funcionario);
    }

    @Override
    public Dependente saveDependente(Dependente dependente) {
        return dependenteJpaRepository.save(dependente);
    }

    @Override
    public Dependente findByCpf(String cpfDependente) {
        return dependenteJpaRepository.findByCpf(cpfDependente)
                .orElseThrow(()-> APIException.build(HttpStatus.BAD_REQUEST, "Não localizado"));
    }

    @Override
    public void deleteDependenteFuncionario(Dependente dependente) {
        dependenteJpaRepository.delete(dependente);
    }
}
