package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.output;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.enums.StatusMatricula;

import java.util.UUID;

public record AlunoResponseDto(
        String nomeAluno,
        UUID idAluno,
        StatusMatricula statusMatricula
) {
}
