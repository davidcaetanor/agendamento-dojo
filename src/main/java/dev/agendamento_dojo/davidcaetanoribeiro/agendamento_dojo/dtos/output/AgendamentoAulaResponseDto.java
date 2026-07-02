package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.output;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.enums.StatusAgendamento;

import java.time.LocalDateTime;

public record AgendamentoAulaResponseDto(
        Long idAgendamento,
        String tituloAula,
        LocalDateTime dataHora,
        LocalDateTime dataHoraFim,
        StatusAgendamento statusAgendamento
) {
}
