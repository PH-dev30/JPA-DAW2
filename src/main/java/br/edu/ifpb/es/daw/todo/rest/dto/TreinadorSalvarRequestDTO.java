package br.edu.ifpb.es.daw.todo.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TreinadorSalvarRequestDTO(

        @NotBlank(message = "O nome do treinador é obrigatório")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome
) {

}