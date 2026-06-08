package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.exceptions.dtos;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public record HttpValidationErrorMessageDto(
        HttpStatus status,
        String mensagem,
        LocalDateTime dataHora,
        List<CampoInvalidoDto> camposInvalidos
) {
}
