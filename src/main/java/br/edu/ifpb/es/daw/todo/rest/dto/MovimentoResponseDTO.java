package br.edu.ifpb.es.daw.todo.rest.dto;

import br.edu.ifpb.es.daw.todo.model.enums.Divisao;
import br.edu.ifpb.es.daw.todo.model.enums.Tipos;

public record MovimentoResponseDTO(
        Long id,
        String nomeDoPoder,
        Integer poder,
        Tipos tipo,
        Divisao tipoDivisao) {

}