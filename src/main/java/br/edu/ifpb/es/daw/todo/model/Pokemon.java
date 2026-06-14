package br.edu.ifpb.es.daw.todo.model;

import br.edu.ifpb.es.daw.todo.model.enums.*;
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
@Table(name = "Pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo1", nullable = false)
    private Tipos tipo1;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo2")
    private Tipos tipo2;

    @Enumerated(EnumType.STRING)
    @Column(name = "raridade", nullable = false)
    private Raridades raridade;

    @Enumerated(EnumType.STRING)
    @Column(name = "geracao", nullable = false)
    private Regioes regioes;

    @Enumerated(EnumType.STRING)
    @Column(name = "habilidade", nullable = false)
    private Habilidades habilidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "natureza", nullable = false)
    private Naturezas natureza;

    @ManyToOne
    @JoinColumn(name = "movimento1_id", nullable = false)
    private Movimento movimento1;

    @ManyToOne
    @JoinColumn(name = "movimento2_id")
    private Movimento movimento2;

    @ManyToOne
    @JoinColumn(name = "movimento3_id")
    private Movimento movimento3;

    @ManyToOne
    @JoinColumn(name = "movimento4_id")
    private Movimento movimento4;

    @OneToMany(mappedBy = "pokemon")
    private List<Selecao> selecoes;


}