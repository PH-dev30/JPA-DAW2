package br.edu.ifpb.es.daw.todo.model;

import br.edu.ifpb.es.daw.todo.model.enums.Divisao;
import br.edu.ifpb.es.daw.todo.model.enums.Tipos;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "movimento")
public class Movimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nomeDoPoder;

    @Column(name = "poder", nullable = false)
    private Integer poder;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private Tipos tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoDivisao")
    private Divisao tipoDivisao;

}