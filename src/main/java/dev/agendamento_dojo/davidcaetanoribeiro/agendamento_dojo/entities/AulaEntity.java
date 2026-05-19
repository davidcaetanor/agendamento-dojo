package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "tb_aula")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AulaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aula_id")
    private Long idAula;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private ProfessorEntity professor;

    @Column(name = "titulo_aula", nullable = false)
    private String tituloAula;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(name = "capacidade_max_aula")
    private Integer capacidadeMaxAula;

}
