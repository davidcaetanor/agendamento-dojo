package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.controllers;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input.AgendamentoRequestDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.output.AgendamentoAulaResponseDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.services.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/agendamentos")
@Tag(name = "Rota de Agendamentos", description = "Rota para visualização e Manipulação de Agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ALUNO')")
    @Operation(summary = "Efetua um agendamento",
            description = "Esse metodo permite que um usuario logado possa efetuar seu agendamento em uma aula.")
    public ResponseEntity<AgendamentoAulaResponseDto> criarAgendamento(@RequestBody @Valid AgendamentoRequestDto requestDto) {
        AgendamentoAulaResponseDto responseDto = agendamentoService.criarAgendamento(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

}
