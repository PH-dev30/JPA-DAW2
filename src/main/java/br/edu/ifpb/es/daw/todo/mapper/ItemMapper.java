package br.edu.ifpb.es.daw.todo.mapper;
import br.edu.ifpb.es.daw.todo.model.Item;
import br.edu.ifpb.es.daw.todo.model.Treinador;
import br.edu.ifpb.es.daw.todo.rest.dto.ItemResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.ItemSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public Item from(ItemSalvarRequestDTO from, Treinador treinador) {
        return Item.builder()
                .nome(from.nome())
                .preco(from.preco())
                .treinador(treinador)
                .build();
    }
    public ItemResponseDTO from(Item from) {
        return new ItemResponseDTO(
                from.getId(),
                from.getNome(),
                from.getPreco(),
                from.getTreinador() != null ? from.getTreinador().getId() : null
        );
    }
}