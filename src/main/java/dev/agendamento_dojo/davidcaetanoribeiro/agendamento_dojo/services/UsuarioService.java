package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.services;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.UsuarioEntity;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.exceptions.UsuarioNaoEncontradoException;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioEntity buscarPorId(UUID id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado!"));
    }
}
