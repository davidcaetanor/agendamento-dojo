package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.services;


import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input.AulaRequestDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.output.AulaResponseDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.AulaEntity;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.exceptions.AulaNaoEncontradaException;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.exceptions.ConflitoDeHorarioException;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.mappers.AulaMapper;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.repositories.AulaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AulaService {
    private final AulaRepository aulaRepository;
    private final AulaMapper aulaMapper;

    public AulaService(AulaRepository aulaRepository, AulaMapper aulaMapper) {
        this.aulaRepository = aulaRepository;
        this.aulaMapper = aulaMapper;
    }

    @Transactional
    public AulaResponseDto criarAula(AulaRequestDto requestDto) {
        if (!horarioAulaValida(requestDto.dataHora(), requestDto.dataHoraFim())) {
            throw new ConflitoDeHorarioException("Horario Inválido, verifique o horario de inicio e fim da aula!");
        }
        if (aulaRepository.existsConflitoHorario(requestDto.idProfessor(), requestDto.dataHora(), requestDto.dataHoraFim())) {
            throw new ConflitoDeHorarioException("Não é possivel fazer o agendamento nesse horario, devido a conflito!");
        }
        AulaEntity aula = aulaMapper.toEntity(requestDto);
        AulaEntity aulaSalva = aulaRepository.save(aula);
        return aulaMapper.toDto(aulaSalva);
    }

    @Transactional(readOnly = true)
    public Page<AulaResponseDto> listarProximasAulas(Pageable pageable) {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        return aulaRepository.findByDataHoraAfter(dataHoraAtual, pageable).map(aulaMapper::toDto);
    }

    @Transactional(readOnly = true)
    public AulaEntity buscarAulaId(Long idAula) {
        return aulaRepository.findById(idAula)
                .orElseThrow(() -> new AulaNaoEncontradaException("Não encontramos nenhuma aula relacionada!"));
    }

    private boolean horarioAulaValida(LocalDateTime dataHora, LocalDateTime dataHoraFim) {
        return dataHora.isBefore(dataHoraFim) && dataHora.isAfter(LocalDateTime.now().plusHours(1));
    }
}
