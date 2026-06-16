package br.edu.ifpb.es.daw.todo.rest.dto;

import br.edu.ifpb.es.daw.todo.model.enums.Cidades;
import br.edu.ifpb.es.daw.todo.model.enums.Locais;
import br.edu.ifpb.es.daw.todo.model.enums.Resultado;

import java.time.LocalDateTime;

public record BatalhaResponseDTO(
        Long id,
        LocalDateTime hora,
        Cidades cidade,
        Locais local,
        Resultado resultado,
        Long treinador1Id,
        Long time1Id,
        Long treinador2Id,
        Long time2Id,
        Long timeVencedorId) {
}