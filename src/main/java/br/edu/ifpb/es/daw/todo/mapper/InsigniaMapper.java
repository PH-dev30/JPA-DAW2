package br.edu.ifpb.es.daw.todo.mapper;

import br.edu.ifpb.es.daw.todo.model.Ginasio;
import br.edu.ifpb.es.daw.todo.model.Insignia;
import br.edu.ifpb.es.daw.todo.model.Treinador;
import br.edu.ifpb.es.daw.todo.rest.dto.InsigniaResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.InsigniaSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class InsigniaMapper {

    public Insignia from(InsigniaSalvarRequestDTO from, Treinador treinador, Ginasio ginasio) {
        return Insignia.builder()
                .nome(from.nome())
                .cidade(from.cidade())
                .dataConquista(from.dataConquista())
                .treinador(treinador)
                .ginasio(ginasio)
                .build();
    }

    public InsigniaResponseDTO from(Insignia from) {
        return new InsigniaResponseDTO(
                from.getId(),
                from.getNome(),
                from.getCidade(),
                from.getDataConquista(),
                from.getTreinador() != null ? from.getTreinador().getId() : null,
                from.getGinasio() != null ? from.getGinasio().getId() : null
        );
    }
}