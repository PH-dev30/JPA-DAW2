package br.edu.ifpb.es.daw.todo.repository;

import br.edu.ifpb.es.daw.todo.model.Movimento;
import br.edu.ifpb.es.daw.todo.model.enums.Tipos;
import br.edu.ifpb.es.daw.todo.model.enums.Divisao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {

    boolean existsByNomeDoPoderIgnoreCase(String nomeDoPoder);

    boolean existsByIdNotAndNomeDoPoderIgnoreCase(Long id, String nomeDoPoder);

    @Query("""
            SELECT m FROM Movimento m
            WHERE m.nomeDoPoder LIKE CONCAT('%', :nomeDoPoder, '%')
            AND (:poderMin IS NULL OR m.poder >= :poderMin)
            AND (:poderMax IS NULL OR m.poder <= :poderMax)
            AND (:tipo IS NULL OR m.tipo = :tipo)
            AND (:tipoDivisao IS NULL OR m.tipoDivisao = :tipoDivisao)
            """)
    Page<Movimento> buscarPor(
            @Param("nomeDoPoder") String nomeDoPoder,
            @Param("poderMin") Integer poderMin,
            @Param("poderMax") Integer poderMax,
            @Param("tipo") Tipos tipo,
            @Param("tipoDivisao") Divisao tipoDivisao,
            Pageable pageable
    );
}