package br.edu.ifpb.es.daw.todo.rest.dto;

import br.edu.ifpb.es.daw.todo.model.enums.Raridades;
import br.edu.ifpb.es.daw.todo.model.enums.Regioes;
import br.edu.ifpb.es.daw.todo.model.enums.Tipos;

public record PokemonBuscaDTO(
        String nome,
        Tipos tipo1,
        Raridades raridade,
        Regioes regioes,
        Integer numeroPagina,
        Integer tamanhoPagina) {

}