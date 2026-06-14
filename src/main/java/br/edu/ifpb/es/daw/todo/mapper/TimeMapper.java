package br.edu.ifpb.es.daw.todo.mapper;

import br.edu.ifpb.es.daw.todo.model.Time;
import br.edu.ifpb.es.daw.todo.model.Treinador;
import br.edu.ifpb.es.daw.todo.rest.dto.TimeResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.TimeSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class TimeMapper {

    public Time from(TimeSalvarRequestDTO from, Treinador treinador) {
        return Time.builder()
                .tipoTime(from.tipo())
                .nome(from.nome())
                .treinador(treinador)
                .build();
    }

    public TimeResponseDTO from(Time from) {
        return new TimeResponseDTO(
                from.getId(),
                from.getNome(),
                from.getTipoTime(),
                from.getTreinador().getId()
        );
    }

}
