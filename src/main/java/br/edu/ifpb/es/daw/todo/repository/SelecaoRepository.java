package br.edu.ifpb.es.daw.todo.repository;

import br.edu.ifpb.es.daw.todo.model.Selecao;
import br.edu.ifpb.es.daw.todo.model.SelecaoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SelecaoRepository extends JpaRepository<Selecao, SelecaoId> {

    @Query("""
            SELECT
            COALESCE(SUM(
                COALESCE(m1.poder,0) +
                COALESCE(m2.poder,0) +
                COALESCE(m3.poder,0) +
                COALESCE(m4.poder,0)
            ),0)
            FROM Selecao s
            LEFT JOIN s.pokemon.movimento1 m1
            LEFT JOIN s.pokemon.movimento2 m2
            LEFT JOIN s.pokemon.movimento3 m3
            LEFT JOIN s.pokemon.movimento4 m4
            WHERE s.time.id = :timeId
            """)
    Integer calcularPoderTotalTime(Long timeId);

}
