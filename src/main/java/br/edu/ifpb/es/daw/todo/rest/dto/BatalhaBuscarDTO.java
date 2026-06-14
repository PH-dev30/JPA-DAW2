package br.edu.ifpb.es.daw.todo.rest.dto;

import br.edu.ifpb.es.daw.todo.model.enums.Resultado;

public record BatalhaBuscarDTO(
        Resultado resultado,
        Long treinador1Id,
        Long treinador2Id) {
}