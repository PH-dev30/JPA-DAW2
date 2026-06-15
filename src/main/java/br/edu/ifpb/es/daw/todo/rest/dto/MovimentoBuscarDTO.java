package br.edu.ifpb.es.daw.todo.rest.dto;

import br.edu.ifpb.es.daw.todo.model.enums.Divisao;
import br.edu.ifpb.es.daw.todo.model.enums.Tipos;

public record MovimentoBuscarDTO(
        String nomeDoPoder,
        Integer poderMin,
        Integer poderMax,
        Tipos tipo,
        Divisao tipoDivisao,
        Integer numeroPagina,
        Integer tamanhoPagina
) {}