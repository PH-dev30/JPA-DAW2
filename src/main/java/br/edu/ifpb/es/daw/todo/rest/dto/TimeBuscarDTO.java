package br.edu.ifpb.es.daw.todo.rest.dto;

import br.edu.ifpb.es.daw.todo.model.enums.TipoTime;

public record TimeBuscarDTO(
        String nome,
        Long treinadorid,
        TipoTime tipoTime) {
}
