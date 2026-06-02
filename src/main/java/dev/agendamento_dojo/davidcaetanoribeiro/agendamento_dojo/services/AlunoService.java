package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.services;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.AlunoEntity;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.enums.RoleUser;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.event.UsuarioRegistradoEvent;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.repositories.AlunoRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @EventListener
    public void criarAluno(UsuarioRegistradoEvent usuarioEvent) {
        if (usuarioEvent.usuario().getRoleUsers().contains(RoleUser.ALUNO)) {
            AlunoEntity aluno = new AlunoEntity();
            aluno.setUsuario(usuarioEvent.usuario());
            aluno.setDataMatricula(LocalDateTime.now());
            alunoRepository.save(aluno);
        }
    }
}
