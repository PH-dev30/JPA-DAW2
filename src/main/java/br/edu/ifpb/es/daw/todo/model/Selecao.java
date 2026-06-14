package br.edu.ifpb.es.daw.todo.model;

import br.edu.ifpb.es.daw.todo.model.enums.Posicao;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Selecao")
public class Selecao {

    @EmbeddedId
    private SelecaoId id;

    @ManyToOne
    @MapsId("timeId")
    private Time time;

    @ManyToOne
    @MapsId("pokemonId")
    private Pokemon pokemon;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Posicao posicao;

}