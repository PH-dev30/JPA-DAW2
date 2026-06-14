package br.edu.ifpb.es.daw.todo.rest.dto;

import br.edu.ifpb.es.daw.todo.model.enums.Itens;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemSalvarRequestDTO(

        @NotNull(message = "O nome do item é obrigatório")
        Itens nome,

        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O preço deve ser maior que zero")
        Double preco,

        @NotNull(message = "O treinador é obrigatório")
        Long treinadorId
) {

}