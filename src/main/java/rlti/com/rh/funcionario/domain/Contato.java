package rlti.com.rh.funcionario.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CONTATO")
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contato_seq_generator")
    @SequenceGenerator(name="contato_seq_generator", sequenceName = "contato_sequence", allocationSize=1)
    @Column(name = "id_contato", nullable = false)
    private Long idContato;

    private String email;
    private String telefone;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String numero;
    private String complemento;
    private String pais;
}
