package br.edu.ifpb.es.daw.todo.rest.dto;
import br.edu.ifpb.es.daw.todo.model.enums.Itens;

public record ItemResponseDTO(
        Long id,
        Itens nome,
        Double preco,
        Long treinadorId) {

}