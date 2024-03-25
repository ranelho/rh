package rlti.com.rh.imposto.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rlti.com.rh.imposto.doman.Inss;
import rlti.com.rh.imposto.doman.Irrf;

import java.time.LocalDate;
import java.util.List;

public interface IrrfJpaRepository  extends JpaRepository<Irrf, Long> {
   /* @Query("SELECT i FROM Irrf i WHERE i.inicioVigencia >= :inicioVigencia " +
            "AND i.fimVigencia <= :fimVigencia")*/
    List<Inss> findAllByInicioVigenciaAndFimVigencia(LocalDate inicioVigencia, LocalDate fimVigencia);

    @Query(value = "SELECT * FROM Irrf WHERE " +
            "inicio_vigencia <= to_date(?1 || '-01', 'YYYY-MM-DD') + interval '1 month' AND " +
            "fim_vigencia >= to_date(?1 || '-01', 'YYYY-MM-DD')", nativeQuery = true)
    List<Irrf> findVigencia(String yearMonth);
}
