package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.services;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.clients.ViaCepClient;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input.AtualizarEnderecoInputDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input.EnderecoViaCepDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.AlunoEntity;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.vo.EnderecoVO;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.enums.RoleUser;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.event.UsuarioRegistradoEvent;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.exceptions.CepNaoEncontradoException;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.mappers.EnderecoMapper;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.repositories.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final ViaCepClient viaCep;
    private final EnderecoMapper enderecoMapper;

    public AlunoService(AlunoRepository alunoRepository, ViaCepClient viaCep, EnderecoMapper enderecoMapper) {
        this.alunoRepository = alunoRepository;
        this.viaCep = viaCep;
        this.enderecoMapper = enderecoMapper;
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

    @Transactional
    public void atualizarMeuEndereco(AtualizarEnderecoInputDto atualizarEnderecoDto) {
        String emailLogado = SecurityContextHolder.getContext().getAuthentication().getName();

        AlunoEntity aluno = alunoRepository.findByUsuario_EmailUsuario(emailLogado)
                .orElseThrow(() -> new UsernameNotFoundException("Não foi localizado nenhum aluno com este email"));

        EnderecoViaCepDto enderecoViaCep = viaCep.buscarEnderecoCep(atualizarEnderecoDto.cep());

        if (enderecoViaCep.erro() != null && enderecoViaCep.erro()) {
            throw new CepNaoEncontradoException("O Cep informado não foi encontrado!");
        }

        EnderecoVO enderecoVO = enderecoMapper.toVO(enderecoViaCep, atualizarEnderecoDto);

        aluno.setEndereco(enderecoVO);
        alunoRepository.save(aluno);
    }

}
