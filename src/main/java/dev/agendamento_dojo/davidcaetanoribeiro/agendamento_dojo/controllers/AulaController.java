package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.controllers;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input.AulaRequestDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.output.AulaResponseDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.services.AulaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/aulas")
public class AulaController {
    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AulaResponseDto> criarAula(@Valid @RequestBody AulaRequestDto aulaRequest) {
        AulaResponseDto aulaResponse = aulaService.criarAula(aulaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(aulaResponse);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<AulaResponseDto>> listarProximasAulas(
            @PageableDefault(size = 5, sort = "dataHora") Pageable pageable) {
        Page<AulaResponseDto> aulaResponsePage = aulaService.listarProximasAulas(pageable);
        return ResponseEntity.status(HttpStatus.CREATED).body(aulaResponsePage);
    }

}
