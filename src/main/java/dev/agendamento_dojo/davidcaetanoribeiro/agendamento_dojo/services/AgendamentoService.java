package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.services;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input.AgendamentoRequestDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.output.AgendamentoAulaResponseDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.AgendamentoEntity;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.AlunoEntity;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.AulaEntity;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.enums.StatusAgendamento;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.exceptions.AgendamentoDuplicadoException;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.exceptions.AgendamentoLotadoException;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.exceptions.AulaExpiradaException;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.mappers.AgendamentoMapper;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.repositories.AgendamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;
    private final AlunoService alunoService;
    private final AulaService aulaService;
    private final AgendamentoMapper agendamentoMapper;

    public AgendamentoService(AgendamentoRepository agendamentoRepository, AlunoService alunoService, AulaService aulaService, AgendamentoMapper agendamentoMapper) {
        this.agendamentoRepository = agendamentoRepository;
        this.alunoService = alunoService;
        this.aulaService = aulaService;
        this.agendamentoMapper = agendamentoMapper;
    }

    @Transactional
    public AgendamentoAulaResponseDto criarAgendamento(AgendamentoRequestDto requestDto) {

        AulaEntity aulaEncontrada = aulaService.buscarAulaId(requestDto.idAula());

        if (agendamentoLotado(aulaEncontrada.getCapacidadeMaxAula(), requestDto.idAula())) {
            throw new AgendamentoLotadoException("Essa aula já está com o agendamento lotado!");
        }

        AlunoEntity alunoEncontrado = alunoService.buscarAlunoLogado();

        alunoService.isMatriculaAtiva(alunoEncontrado.getStatusMatricula());

        if (!aulaPosteriorAoAgendamento(aulaEncontrada.getDataHora())) {
            throw new AulaExpiradaException("Não é possivel agendar para um horario posterior ao atual");
        }

        if (agendamentoRepository.existsAlunoJaAgendado(aulaEncontrada.getIdAula(), alunoEncontrado.getIdAluno(), StatusAgendamento.CONFIRMADA)) {
            throw new AgendamentoDuplicadoException("Aluno já possui agendamento a essa aula!");
        }

        AgendamentoEntity agendamentoRealizado = new AgendamentoEntity();
        agendamentoRealizado.setAlunoAgendado(alunoEncontrado);
        agendamentoRealizado.setAulaAgendada(aulaEncontrada);
        agendamentoRealizado.setStatusAgendamento(StatusAgendamento.CONFIRMADA);
        AgendamentoEntity agendamentoSalvo = agendamentoRepository.save(agendamentoRealizado);

        return agendamentoMapper.toDto(agendamentoSalvo);
    }


    private boolean agendamentoLotado(int capacidadeMaxima, Long idAula) {
        long lotacaoAtual = agendamentoRepository.countAulasAgendadas(idAula, StatusAgendamento.CONFIRMADA);
        return lotacaoAtual >= capacidadeMaxima;
    }

    private boolean aulaPosteriorAoAgendamento(LocalDateTime dataHora) {
        return dataHora.isAfter(LocalDateTime.now());
    }
}
