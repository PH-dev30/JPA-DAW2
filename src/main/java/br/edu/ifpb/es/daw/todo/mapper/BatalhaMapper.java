
package br.edu.ifpb.es.daw.todo.mapper;

import br.edu.ifpb.es.daw.todo.model.Batalha;
import br.edu.ifpb.es.daw.todo.model.Time;
import br.edu.ifpb.es.daw.todo.model.Treinador;
import br.edu.ifpb.es.daw.todo.rest.dto.BatalhaResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.BatalhaSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class BatalhaMapper {
    public Batalha from(BatalhaSalvarRequestDTO from, Treinador t1, Treinador t2, Time timeVencedor) {
        return Batalha.builder()
                .hora(from.hora())
                .cidade(from.cidade())
                .local(from.local())
                .resultado(from.resultado())
                .treinador1(t1)
                .treinador2(t2)
                .timeVencedor(timeVencedor)
                .build();
    }

    public BatalhaResponseDTO from(Batalha from) {
        return new BatalhaResponseDTO(
                from.getId(),
                from.getHora(),
                from.getCidade(),
                from.getLocal(),
                from.getResultado(),
                from.getTreinador1() != null ? from.getTreinador1().getId() : null,
                from.getTreinador2() != null ? from.getTreinador2().getId() : null,
                from.getTimeVencedor() != null ? from.getTimeVencedor().getId() : null
        );
    }
}