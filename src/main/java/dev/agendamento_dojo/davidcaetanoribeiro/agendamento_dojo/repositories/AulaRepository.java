package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.repositories;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.AulaEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.UUID;

public interface AulaRepository extends JpaRepository<AulaEntity, Long> {

    @Query("""
            SELECT COUNT(t1) > 0
            FROM AulaEntity t1
                WHERE t1.professor.idProfessor  = :prof_id
                    AND t1.dataHora < :data_fim
                    AND t1.dataHoraFim > :data_inicio
            
            """)
    boolean existsConflitoHorario(
            @Param("prof_id") UUID idProfessor,
            @Param("data_inicio") LocalDateTime dataInicio,
            @Param("data_fim") LocalDateTime dataFim);

    Page<AulaEntity> findByDataHoraAfter(LocalDateTime dataHoraAtual, Pageable pageable);
}
