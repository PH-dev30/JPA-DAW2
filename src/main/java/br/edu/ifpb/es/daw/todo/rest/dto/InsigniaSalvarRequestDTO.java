package br.edu.ifpb.es.daw.todo.rest.dto;

import br.edu.ifpb.es.daw.todo.model.enums.Cidades;
import br.edu.ifpb.es.daw.todo.model.enums.Insignias;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record InsigniaSalvarRequestDTO(

        @NotNull(message = "O nome da insígnia é obrigatório")
        Insignias nome,

        @NotNull(message = "A cidade é obrigatória")
        Cidades cidade,

        @NotNull(message = "A data de conquista é obrigatória")
        LocalDate dataConquista,

        @NotNull(message = "O treinador é obrigatório")
        Long treinadorId,

        @NotNull(message = "O ginásio é obrigatório")
        Long ginasioId
) {

}