package rlti.com.rh.funcionario.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import rlti.com.rh.funcionario.domain.Funcionario;
import rlti.com.rh.funcionario.repository.DependenteRepository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class DependenteInfraRepository implements DependenteRepository {

    private final DepenndeteJpaRepository dependenteJpaRepository;

    @Override
    public int countDependenteFuncionario(Funcionario funcionario) {
        return dependenteJpaRepository.countByFuncionario(funcionario);
    }
}
