package br.edu.ifpb.es.daw.todo.repository;

import br.edu.ifpb.es.daw.todo.model.Selecao;
import br.edu.ifpb.es.daw.todo.model.SelecaoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelecaoRepository extends JpaRepository<Selecao, SelecaoId> {
}
