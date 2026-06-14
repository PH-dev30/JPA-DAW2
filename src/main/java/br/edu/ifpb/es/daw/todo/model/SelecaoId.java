package br.edu.ifpb.es.daw.todo.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SelecaoId implements Serializable {
    private Long timeId;
    private Long pokemonId;


}
