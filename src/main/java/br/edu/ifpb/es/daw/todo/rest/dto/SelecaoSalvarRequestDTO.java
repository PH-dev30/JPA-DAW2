package br.edu.ifpb.es.daw.todo.rest.dto;

import br.edu.ifpb.es.daw.todo.model.enums.Posicao;
import jakarta.validation.constraints.NotNull;

public record SelecaoSalvarRequestDTO(

        @NotNull(message = "O time é obrigatório")
        Long timeId,

        @NotNull(message = "O Pokémon é obrigatório")
        Long pokemonId,

        @NotNull(message = "A posição é obrigatória")
        Posicao posicao
) {

}