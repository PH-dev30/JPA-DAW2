package br.edu.ifpb.es.daw.todo.rest.dto;

import br.edu.ifpb.es.daw.todo.model.enums.TipoTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TimeSalvarRequestDTO(

        @NotBlank(message = "O nome do time é obrigatório")
        @Size(min = 3, max = 50, message = "O nome do time deve ter entre 3 e 50 caracteres")
        String nome,

        @NotNull(message = "O tipo do time é obrigatório")
        TipoTime tipo,

        @NotNull(message = "O treinador é obrigatório")
        Long treinadorId
) {

}