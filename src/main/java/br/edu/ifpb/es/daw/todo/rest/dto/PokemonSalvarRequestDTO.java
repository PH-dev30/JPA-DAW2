package br.edu.ifpb.es.daw.todo.rest.dto;

import br.edu.ifpb.es.daw.todo.model.enums.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PokemonSalvarRequestDTO(

        @NotBlank(message = "O nome do Pokémon é obrigatório")
        @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
        String nome,

        @NotNull(message = "O tipo principal é obrigatório")
        Tipos tipo1,

        Tipos tipo2,

        @NotNull(message = "A raridade é obrigatória")
        Raridades raridade,

        @NotNull(message = "A região é obrigatória")
        Regioes regioes,

        @NotNull(message = "A habilidade é obrigatória")
        Habilidades habilidade,

        @NotNull(message = "A natureza é obrigatória")
        Naturezas natureza,

        @NotNull(message = "O movimento 1 é obrigatório")
        Long movimento1Id,

        Long movimento2Id,
        Long movimento3Id,
        Long movimento4Id
) {

}