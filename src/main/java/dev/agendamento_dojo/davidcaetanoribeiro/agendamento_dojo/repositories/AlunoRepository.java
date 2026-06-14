package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.repositories;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface AlunoRepository extends JpaRepository<AlunoEntity, UUID> {

    Optional<AlunoEntity> findByUsuario_EmailUsuario(String usuarioEmailUsuario);
}
