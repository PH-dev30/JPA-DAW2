package br.edu.ifpb.es.daw.todo.rest.dto;

import br.edu.ifpb.es.daw.todo.model.enums.TipoTime;

public record TimeResponseDTO(
        Long id,
        String nome,
        TipoTime tipo,
        Long treinadorId) {

}