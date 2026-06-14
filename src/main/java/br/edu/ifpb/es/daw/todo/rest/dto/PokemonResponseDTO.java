package br.edu.ifpb.es.daw.todo.rest.dto;

import br.edu.ifpb.es.daw.todo.model.enums.*;

public record PokemonResponseDTO(
        Long id,
        String nome,
        Tipos tipo1,
        Tipos tipo2,
        Raridades raridade,
        Regioes regioes,
        Habilidades habilidade,
        Naturezas natureza,
        Long movimento1Id,
        Long movimento2Id,
        Long movimento3Id,
        Long movimento4Id) {

}