package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.services;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.ProfessorEntity;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.UsuarioEntity;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.enums.RoleUser;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.event.UsuarioPromovidoProfessorEvent;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.repositories.ProfessorRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {
    private final ProfessorRepository profRepository;
    private final UsuarioService usuarioService;

    public ProfessorService(ProfessorRepository profRepository, UsuarioService usuarioService) {
        this.profRepository = profRepository;
        this.usuarioService = usuarioService;
    }

    @EventListener
    @Transactional
    public void criarProfessor(UsuarioPromovidoProfessorEvent professorEvent) {
        UsuarioEntity usuarioEncontrado = usuarioService.buscarPorId(professorEvent.idUsuario());

        if (usuarioEncontrado != null && usuarioEncontrado.getRoleUsers().contains(RoleUser.PROFESSOR)) {
            ProfessorEntity professor = new ProfessorEntity();
            professor.setUsuario(usuarioEncontrado);
            profRepository.save(professor);
        }
    }

}
