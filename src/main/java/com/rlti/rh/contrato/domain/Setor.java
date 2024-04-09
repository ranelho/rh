package com.rlti.rh.contrato.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.rlti.rh.contrato.application.request.SetorRequest;

import static com.rlti.rh.utils.Utils.formatText;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "SETOR")
public class Setor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "setor_seq_generator")
    @SequenceGenerator(name="setor_seq_generator", sequenceName = "setor_sequence", allocationSize=1)
    @Column(name = "id_setor", nullable = false)
    private Long idSetor;

    private String nomeSetor;

    public Setor(SetorRequest request) {
        this.nomeSetor = formatText(request.nomeSetor());
    }
}
