package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record AulaRequestDto(
        @NotBlank
        String tituloAula,

        @NotNull
        @Future
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime dataHora,

        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime dataHoraFim,

        @NotNull
        Integer capacidadeMaxAula,

        @NotNull
        UUID idProfessor

) {
}
