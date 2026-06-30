package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.enums.StatusAgendamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "tb_agendamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoEntity {

    @Id
    @GeneratedValue
    @Column(name = "agendamento_id")
    private Long idAgendamento;

    @ManyToOne
    @JoinColumn(name = "aula_id", nullable = false)
    private AulaEntity aulaAgendada;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private AlunoEntity alunoAgendado;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_agendamento")
    private StatusAgendamento statusAgendamento;
}
