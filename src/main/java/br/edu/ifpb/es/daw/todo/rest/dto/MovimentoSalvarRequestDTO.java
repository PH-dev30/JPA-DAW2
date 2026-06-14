package br.edu.ifpb.es.daw.todo.rest.dto;

import br.edu.ifpb.es.daw.todo.model.enums.Divisao;
import br.edu.ifpb.es.daw.todo.model.enums.Tipos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record MovimentoSalvarRequestDTO(

        @NotBlank(message = "O nome do poder é obrigatório")
        @Size(min = 3, max = 100, message = "O nome do poder deve ter entre 3 e 100 caracteres")
        String nomeDoPoder,

        @NotNull(message = "O poder é obrigatório")
        @Positive(message = "O poder deve ser maior que zero")
        Integer poder,

        @NotNull(message = "O tipo é obrigatório")
        Tipos tipo,

        @NotNull(message = "A divisão é obrigatória")
        Divisao tipoDivisao
) {

}