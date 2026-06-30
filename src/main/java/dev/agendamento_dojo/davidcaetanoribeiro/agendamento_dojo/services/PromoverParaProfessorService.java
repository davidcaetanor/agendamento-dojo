package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.services;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.output.PromoverProfessorResponseDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.UsuarioEntity;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.enums.RoleUser;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.event.UsuarioPromovidoProfessorEvent;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.exceptions.UsuarioNaoEncontradoException;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.repositories.UsuarioRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public PromoverProfessorResponseDto execute(String email) {
        UsuarioEntity usuarioEncontrado = usuarioRepository.findUsuarioEntitiesByEmailUsuario(email)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Não foi encontrado nenhum usuário com esse email!"));

        if (!usuarioEncontrado.getRoleUsers().contains(RoleUser.PROFESSOR)) {
            usuarioEncontrado.setRoleUsers(Set.of(RoleUser.PROFESSOR));
            eventPublisher.publishEvent(new UsuarioPromovidoProfessorEvent(usuarioEncontrado.getIdUsuario()));
        }

        return new PromoverProfessorResponseDto(usuarioEncontrado.getNomeUsuario(), "Promovido a Professor!", usuarioEncontrado.getIdUsuario());
    }
}
