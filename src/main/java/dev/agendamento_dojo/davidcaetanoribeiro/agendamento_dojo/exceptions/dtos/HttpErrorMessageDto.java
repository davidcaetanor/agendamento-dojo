package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.exceptions.dtos;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record HttpErrorMessageDto(HttpStatus status, String mensagem, LocalDateTime dataHora) {
}
