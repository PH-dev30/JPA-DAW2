package br.edu.ifpb.es.daw.todo.rest.dto;
import br.edu.ifpb.es.daw.todo.model.enums.Cidades;
import br.edu.ifpb.es.daw.todo.model.enums.Insignias;
import java.time.LocalDate;

public record InsigniaResponseDTO(
        Long id,
        Insignias nome,
        Cidades cidade,
        LocalDate dataConquista,
        Long treinadorId,
        Long ginasioId) {

}