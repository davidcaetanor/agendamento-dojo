package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.repositories;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.AgendamentoEntity;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.enums.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {

    @Query("""
            SELECT COUNT(t1)
            FROM AgendamentoEntity AS t1
            WHERE t1.aulaAgendada.idAula= :aula_id
            AND t1.statusAgendamento= :status_agendamento""")
    long countAulasAgendadas(
            @Param("aula_id") long idAula,
            @Param("status_agendamento") StatusAgendamento statusAgendamento);

    @Query("""
            SELECT COUNT(t1) > 0
            FROM AgendamentoEntity AS t1
            WHERE t1.aulaAgendada.idAula= :aula_id
            AND t1.alunoAgendado.idAluno= :aluno_id
            AND t1.statusAgendamento= :status_agendamento""")
    boolean existsAlunoJaAgendado(
            @Param("aula_id") Long idAula,
            @Param("aluno_id") UUID idAluno,
            @Param("status_agendamento") StatusAgendamento statusAgendamento);
}
