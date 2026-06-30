package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.output;

import java.time.LocalDateTime;
import java.util.UUID;

public record AulaResponseDto(
        Long idAula,
        String tituloAula,
        LocalDateTime dataHora,
        LocalDateTime dataHoraFim,
        Integer capacidadeMaxAula,
        UUID idProfessor,
        String nomeProfessor
) {
}
