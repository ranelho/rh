package rlti.com.rh.funcionario.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import rlti.com.rh.funcionario.domain.Contato;

public interface ContatoJpaRepository extends JpaRepository<Contato, Long> {
}
