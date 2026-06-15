package br.edu.ifpb.es.daw.todo.repository;

import br.edu.ifpb.es.daw.todo.model.Pokemon;
import br.edu.ifpb.es.daw.todo.model.enums.Raridades;
import br.edu.ifpb.es.daw.todo.model.enums.Regioes;
import br.edu.ifpb.es.daw.todo.model.enums.Tipos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {


    @Query("""
                SELECT p FROM Pokemon p
                WHERE p.nome LIKE CONCAT('%', :nome, '%')
                AND (:tipo1 IS NULL OR p.tipo1 = :tipo1)
                AND (:raridade IS NULL OR p.raridade = :raridade)
                AND (:regioes IS NULL OR p.regioes = :regioes)
            """)
    Page<Pokemon> buscarPor(
            @Param("nome") String nome,
            @Param("tipo1") Tipos tipo1,
            @Param("raridade") Raridades raridade,
            @Param("regioes") Regioes regioes,
            Pageable pageable
    );

    @Query("""
                SELECT p FROM Pokemon p
                WHERE p.movimento1.id = :movimentoId
                   OR p.movimento2.id = :movimentoId
                   OR p.movimento3.id = :movimentoId
                   OR p.movimento4.id = :movimentoId
            """)
    List<Pokemon> findByMovimentoId(@Param("movimentoId") Long movimentoId);

}
