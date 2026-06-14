package br.edu.ifpb.es.daw.todo.rest.dto;

import br.edu.ifpb.es.daw.todo.model.enums.Cidades;
import br.edu.ifpb.es.daw.todo.model.enums.GinasioNome;
import br.edu.ifpb.es.daw.todo.model.enums.Tipos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GinasioSalvarRequestDTO(

        @NotBlank(message = "O nome do líder é obrigatório")
        @Size(min = 3, max = 100, message = "O nome do líder deve ter entre 3 e 100 caracteres")
        String lider,

        @NotNull(message = "O nome do ginásio é obrigatório")
        GinasioNome nome,

        @NotNull(message = "A cidade é obrigatória")
        Cidades cidade,

        @NotNull(message = "O tipo é obrigatório")
        Tipos tipo,

        @NotNull(message = "O time é obrigatório")
        Long timeId
) {

}