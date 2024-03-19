package rlti.com.rh.imposto.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import rlti.com.rh.imposto.doman.Inss;
import rlti.com.rh.imposto.doman.Irrf;

import java.time.LocalDate;
import java.util.List;

public interface IrrfJpaRepository  extends JpaRepository<Irrf, Long> {
   /* @Query("SELECT i FROM Irrf i WHERE i.inicioVigencia >= :inicioVigencia " +
            "AND i.fimVigencia <= :fimVigencia")*/
    List<Inss> findByInicioVigenciaAndFimVigencia(LocalDate inicioVigencia, LocalDate fimVigencia);
}
