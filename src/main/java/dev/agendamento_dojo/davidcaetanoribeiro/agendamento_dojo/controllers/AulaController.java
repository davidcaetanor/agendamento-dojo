package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.controllers;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input.AulaRequestDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.output.AulaResponseDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.services.AulaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Rota de Aulas", description = "Rota para visualização e manipulação de aulas")
public class AulaController {
    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Criação da Aula",
            description = "Partindo de um usuario com perfil de administrador, permite que ele crie uma Aula relacionando o Professor vigente")
    public ResponseEntity<AulaResponseDto> criarAula(@Valid @RequestBody AulaRequestDto aulaRequest) {
        AulaResponseDto aulaResponse = aulaService.criarAula(aulaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(aulaResponse);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Visualização das Próximas Aulas",
            description = "Permite que usuarios logados no sistema visualizem a grade com as próximas aulas")
    public ResponseEntity<Page<AulaResponseDto>> listarProximasAulas(
            @PageableDefault(size = 5, sort = "dataHora") Pageable pageable) {
        Page<AulaResponseDto> aulaResponsePage = aulaService.listarProximasAulas(pageable);
        return ResponseEntity.status(HttpStatus.CREATED).body(aulaResponsePage);
    }

}
