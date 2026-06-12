package br.edu.ifpb.es.daw.todo.mapper;

import br.edu.ifpb.es.daw.todo.model.Movimento;
import br.edu.ifpb.es.daw.todo.rest.dto.MovimentoResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.MovimentoSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class MovimentoMapper {

    public Movimento from(MovimentoSalvarRequestDTO from) {
        return Movimento.builder()
                .nomeDoPoder(from.nomeDoPoder())
                .poder(from.poder())
                .tipo(from.tipo())
                .tipoDivisao(from.tipoDivisao())
                .build();
    }

    public MovimentoResponseDTO from(Movimento from) {
        return new MovimentoResponseDTO(
                from.getId(),
                from.getNomeDoPoder(),
                from.getPoder(),
                from.getTipo(),
                from.getTipoDivisao()
        );
    }
}