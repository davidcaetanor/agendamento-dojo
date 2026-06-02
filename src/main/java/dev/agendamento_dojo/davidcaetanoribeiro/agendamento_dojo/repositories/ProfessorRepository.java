package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.repositories;


import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, UUID> {
}
