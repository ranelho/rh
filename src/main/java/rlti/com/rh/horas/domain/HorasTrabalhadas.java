package rlti.com.rh.horas.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "HORAS_TRABALHADAS")
public class HorasTrabalhadas {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_horas_trabalhadas", nullable = false)
    private Long idHorasTrabalhadas;

    private int quantidadeHoras;
    private Long idCargoFuncionario;

}
