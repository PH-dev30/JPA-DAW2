package br.edu.ifpb.es.daw.todo.model;

import br.edu.ifpb.es.daw.todo.model.enums.Cidades;
import br.edu.ifpb.es.daw.todo.model.enums.Locais;
import br.edu.ifpb.es.daw.todo.model.enums.Resultado;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Batalha")
public class Batalha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "hora", nullable = false)
    private LocalDateTime hora;

    @Enumerated(EnumType.STRING)
    @Column(name = "cidade")
    private Cidades cidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "local", nullable = false)
    private Locais local;

    @Enumerated(EnumType.STRING)
    private Resultado resultado;

    @ManyToOne
    @JoinColumn(name = "treinador1_id", nullable = false)
    private Treinador treinador1;

    @ManyToOne
    @JoinColumn(name = "treinador2_id", nullable = false)
    private Treinador treinador2;

    @ManyToOne
    @JoinColumn(name = "Time1")
    private Time time1;

    @ManyToOne
    @JoinColumn(name = "Time2")
    private Time time2;

    @ManyToOne
    @JoinColumn(name = "time_vencedor_id")
    private Time timeVencedor;

}
