package br.edu.ifpb.es.daw.todo.model;

import br.edu.ifpb.es.daw.todo.model.enums.Itens;
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
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "nome", nullable = false)
    private Itens nome;

    @Column(name = "preco", nullable = false)
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "treinador_id")
    private Treinador treinador;


}