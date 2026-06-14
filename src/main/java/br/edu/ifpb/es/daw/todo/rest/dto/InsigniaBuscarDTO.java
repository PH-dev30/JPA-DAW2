package br.edu.ifpb.es.daw.todo.rest.dto;
import br.edu.ifpb.es.daw.todo.model.enums.Insignias;

public record InsigniaBuscarDTO(
        Insignias nome,
        Long treinadorId,
        Long ginasioId) {

}