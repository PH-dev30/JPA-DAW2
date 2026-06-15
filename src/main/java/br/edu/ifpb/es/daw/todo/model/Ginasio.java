package br.edu.ifpb.es.daw.todo.model;

import br.edu.ifpb.es.daw.todo.model.enums.Cidades;
import br.edu.ifpb.es.daw.todo.model.enums.GinasioNome;
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
@Table(name = "Ginasio")
public class Ginasio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "Lider", nullable = false)
    private String lider;

    @Enumerated(EnumType.STRING)
    @Column(name = "nome_ginasio", nullable = false)
    private GinasioNome nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "cidade", nullable = false)
    private Cidades cidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "Especialidade", nullable = false)
    private Tipos tipo;

    ToOne
    @JoinColumn(name = "time_id", nullable = false)
    private Time time;

}
