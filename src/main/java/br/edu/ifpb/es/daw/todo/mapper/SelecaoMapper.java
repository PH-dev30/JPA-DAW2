
package br.edu.ifpb.es.daw.todo.mapper;

import br.edu.ifpb.es.daw.todo.model.Pokemon;
import br.edu.ifpb.es.daw.todo.model.Selecao;
import br.edu.ifpb.es.daw.todo.model.SelecaoId;
import br.edu.ifpb.es.daw.todo.model.Time;
import br.edu.ifpb.es.daw.todo.rest.dto.SelecaoResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.SelecaoSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class SelecaoMapper {
    public Selecao from(SelecaoSalvarRequestDTO from, Time time, Pokemon pokemon) {
        SelecaoId selecaoId = new SelecaoId();
        selecaoId.setTimeId(time.getId());
        selecaoId.setPokemonId(pokemon.getId());
        return Selecao.builder()
                .id(selecaoId)
                .time(time)
                .pokemon(pokemon)
                .posicao(from.posicao())
                .build();
    }

    public SelecaoResponseDTO from(Selecao from) {
        return new SelecaoResponseDTO(
                from.getId().getTimeId(),
                from.getId().getPokemonId(),
                from.getPosicao()
        );
    }
}