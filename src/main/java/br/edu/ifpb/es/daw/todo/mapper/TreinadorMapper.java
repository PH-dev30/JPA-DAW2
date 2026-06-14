package br.edu.ifpb.es.daw.todo.mapper;

import br.edu.ifpb.es.daw.todo.model.Treinador;
import br.edu.ifpb.es.daw.todo.rest.dto.TreinadorResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.TreinadorSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class TreinadorMapper {

    public Treinador from(TreinadorSalvarRequestDTO from) {
        return Treinador.builder()
                .nome(from.nome())
                .build();
    }

    public TreinadorResponseDTO from(Treinador from) {
        return new TreinadorResponseDTO(
                from.getId(),
                from.getNome()
        );

    }

}
