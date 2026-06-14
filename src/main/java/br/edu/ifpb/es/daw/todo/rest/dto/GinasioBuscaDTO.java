package br.edu.ifpb.es.daw.todo.rest.dto;

import br.edu.ifpb.es.daw.todo.model.enums.Cidades;
import br.edu.ifpb.es.daw.todo.model.enums.GinasioNome;
import br.edu.ifpb.es.daw.todo.model.enums.Tipos;

public record GinasioBuscaDTO(
        String lider,
        GinasioNome nome,
        Cidades cidade,
        Tipos tipo) {
}
