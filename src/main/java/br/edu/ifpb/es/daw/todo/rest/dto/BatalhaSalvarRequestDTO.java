package br.edu.ifpb.es.daw.todo.rest.dto;

import br.edu.ifpb.es.daw.todo.model.enums.Cidades;
import br.edu.ifpb.es.daw.todo.model.enums.Locais;
import br.edu.ifpb.es.daw.todo.model.enums.Resultado;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record BatalhaSalvarRequestDTO(

        @NotNull(message = "A hora da batalha é obrigatória")
        LocalDateTime hora,

        @NotNull(message = "A cidade é obrigatória")
        Cidades cidade,

        @NotNull(message = "O local é obrigatório")
        Locais local,

        @NotNull(message = "O resultado é obrigatório")
        Resultado resultado,

        @NotNull(message = "O treinador 1 é obrigatório")
        Long treinador1Id,

        @NotNull(message = "O treinador 2 é obrigatório")
        Long treinador2Id,

        @NotNull(message = "O time vencedor é obrigatório")
        Long timeVencedorId
) {

}