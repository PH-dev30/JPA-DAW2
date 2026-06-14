package br.edu.ifpb.es.daw.todo.model;

import br.edu.ifpb.es.daw.todo.model.enums.Cidades;
import br.edu.ifpb.es.daw.todo.model.enums.Insignias;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Insignia")
public class Insignia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "nome", nullable = false)
    private Insignias nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "cidade", nullable = false)
    private Cidades cidade;

    @ManyToOne
    @JoinColumn(name = "ginasio_id", nullable = false)
    private Ginasio ginasio;

    @Column(name = "data_conquista", nullable = false)
    private LocalDate dataConquista;

    @ManyToOne
    @JoinColumn(name = "treinador_id", nullable = false)
    private Treinador treinador;


}