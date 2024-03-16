package rlti.com.rh.funcionario.infra;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import rlti.com.rh.funcionario.domain.Funcionario;
import rlti.com.rh.funcionario.repository.FuncionarioRepository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class FuncionarioInfraRepository implements FuncionarioRepository {
    private final FuncionarioJpaRepository funcionarioJpaRepository;

    @Override
    public boolean save(Funcionario funcionario) {
        try {
            funcionarioJpaRepository.save(funcionario);
            return true;
        }catch (Exception e){
            return Boolean.parseBoolean(e.getMessage());
        }
    }
}
