package rlti.com.rh.imposto.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import rlti.com.rh.imposto.doman.Irrf;

public interface IrrfJpaRepository  extends JpaRepository<Irrf, Long> {
}
