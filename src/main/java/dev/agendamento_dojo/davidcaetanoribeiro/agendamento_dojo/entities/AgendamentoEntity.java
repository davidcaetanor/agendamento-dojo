package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities;

import jakarta.persistence.*;



@Entity
@Table(name = "tb_agendamento")
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
}
