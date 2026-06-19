package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.services;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.output.PromoverProfessorOutputDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.UsuarioEntity;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.enums.RoleUser;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.event.UsuarioPromovidoProfessorEvent;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.exceptions.UsuarioNaoEncontradoException;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PromoverParaProfessorService {
    private final UsuarioRepository usuarioRepository;
    private final ApplicationEventPublisher eventPublisher;

    public PromoverParaProfessorService(UsuarioRepository usuarioRepository, ApplicationEventPublisher eventPublisher) {
        this.usuarioRepository = usuarioRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public PromoverProfessorOutputDto execute(String email) {
        UsuarioEntity usuarioEncontrado = usuarioRepository.findUsuarioEntitiesByEmailUsuario(email)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Não foi encontrado nenhum usuário com esse email!"));

        if (!usuarioEncontrado.getRoleUsers().contains(RoleUser.PROFESSOR)) {
            usuarioEncontrado.setRoleUsers(Set.of(RoleUser.PROFESSOR));
            eventPublisher.publishEvent(new UsuarioPromovidoProfessorEvent(usuarioEncontrado.getIdUsuario()));
        }

        return new PromoverProfessorOutputDto(usuarioEncontrado.getNomeUsuario(), "Promovido a Professor!");
    }
}
