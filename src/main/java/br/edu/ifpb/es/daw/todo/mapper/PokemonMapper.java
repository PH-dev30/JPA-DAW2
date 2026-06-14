package br.edu.ifpb.es.daw.todo.mapper;

import br.edu.ifpb.es.daw.todo.model.Movimento;
import br.edu.ifpb.es.daw.todo.model.Pokemon;
import br.edu.ifpb.es.daw.todo.rest.dto.PokemonResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.PokemonSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class PokemonMapper {

    public Pokemon from(PokemonSalvarRequestDTO from, Movimento movimento1, Movimento movimento2, Movimento movimento3, Movimento movimento4) {
        return Pokemon.builder()
                .nome(from.nome())
                .tipo1(from.tipo1())
                .tipo2(from.tipo2())
                .raridade(from.raridade())
                .regioes(from.regioes())
                .habilidade(from.habilidade())
                .natureza(from.natureza())
                .movimento1(movimento1)
                .movimento2(movimento2)
                .movimento3(movimento3)
                .movimento4(movimento4)
                .build();
    }

    public PokemonResponseDTO from(Pokemon from) {
        return new PokemonResponseDTO(
                from.getId(),
                from.getNome(),
                from.getTipo1(),
                from.getTipo2(),
                from.getRaridade(),
                from.getRegioes(),
                from.getHabilidade(),
                from.getNatureza(),
                from.getMovimento1() != null ? from.getMovimento1().getId() : null,
                from.getMovimento2() != null ? from.getMovimento2().getId() : null,
                from.getMovimento3() != null ? from.getMovimento3().getId() : null,
                from.getMovimento4() != null ? from.getMovimento4().getId() : null
        );
    }
}
