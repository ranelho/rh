package rlti.com.rh.imposto.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import rlti.com.rh.imposto.doman.Inss;

public interface InssJpaRepository extends JpaRepository<Inss,Long> {
}
