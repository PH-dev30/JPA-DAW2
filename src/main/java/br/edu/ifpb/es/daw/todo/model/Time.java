package br.edu.ifpb.es.daw.todo.model;

import br.edu.ifpb.es.daw.todo.model.enums.TipoTime;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "time")
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoTime tipoTime;

    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "treinador_id", nullable = false)
    private Treinador treinador;

    @OneToMany(mappedBy = "time")
    private List<Selecao> selecoes;


}