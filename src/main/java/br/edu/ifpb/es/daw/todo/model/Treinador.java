package br.edu.ifpb.es.daw.todo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "treinador")
public class Treinador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "treinador")
    private List<Time> times;

    @OneToMany(mappedBy = "treinador")
    private List<Item> itens;

    @OneToMany(mappedBy = "treinador")
    private List<Insignia> insignias;

}