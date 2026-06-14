package br.edu.ifpb.es.daw.todo.mapper;

import br.edu.ifpb.es.daw.todo.model.Ginasio;
import br.edu.ifpb.es.daw.todo.model.Time;
import br.edu.ifpb.es.daw.todo.rest.dto.GinasioResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.GinasioSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class GinasioMapper {

    public Ginasio from(GinasioSalvarRequestDTO from, Time time) {
        return Ginasio.builder()
                .lider(from.lider())
                .nome(from.nome())
                .cidade(from.cidade())
                .tipo(from.tipo())
                .time(time)
                .build();
    }

    public GinasioResponseDTO from(Ginasio from) {
        return new GinasioResponseDTO(
                from.getId(),
                from.getLider(),
                from.getNome(),
                from.getCidade(),
                from.getTipo(),
                from.getTime().getId()
                );
    }
}
