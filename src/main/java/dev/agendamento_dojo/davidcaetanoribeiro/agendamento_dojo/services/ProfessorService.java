package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.services;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.ProfessorEntity;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.enums.RoleUser;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.event.UsuarioRegistradoEvent;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.repositories.ProfessorRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {
    private final ProfessorRepository profRepository;

    public ProfessorService(ProfessorRepository profRepository) {
        this.profRepository = profRepository;
    }

    @EventListener
    public void criarProfessor(UsuarioRegistradoEvent usuarioEvent) {
        if (usuarioEvent.usuario().getRoleUsers().contains(RoleUser.PROFESSOR)) {
            ProfessorEntity professor = new ProfessorEntity();
            professor.setUsuario(usuarioEvent.usuario());
            profRepository.save(professor);
        }
    }

}
