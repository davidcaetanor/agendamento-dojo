package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.output;

import java.util.UUID;

public record PromoverProfessorResponseDto(
        String nomeProfessor,
        String mensagem,
        UUID idProfessor
) {
}
