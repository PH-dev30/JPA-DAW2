
package br.edu.ifpb.es.daw.todo.rest.dto;

import br.edu.ifpb.es.daw.todo.model.enums.Posicao;

public record SelecaoResponseDTO(
        Long timeId,
        Long pokemonId,
        Posicao posicao) {
}