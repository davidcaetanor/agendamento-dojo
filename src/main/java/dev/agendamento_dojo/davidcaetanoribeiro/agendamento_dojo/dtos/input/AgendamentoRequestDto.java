package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input;

import jakarta.validation.constraints.NotNull;

public record AgendamentoRequestDto(
        @NotNull(message = "Informe uma aula!")
        Long idAula
) {
}
