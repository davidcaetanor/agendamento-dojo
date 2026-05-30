package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.repositories;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {

    Optional<UsuarioEntity> findUsuarioEntitiesByEmailUsuario(String emailUsuario);

    boolean existsByEmailUsuario(String emailUsuario);
}
