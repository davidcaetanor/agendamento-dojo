package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.controllers.internal;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.output.PromoverProfessorResponseDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.services.PromoverParaProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/internal/admin/usuarios")
@Tag(name = "Rota interna para Admin")
public class AdminUsuarioController {
    private final PromoverParaProfessorService promoverService;

    public AdminUsuarioController(PromoverParaProfessorService promoverService) {
        this.promoverService = promoverService;
    }

    @PatchMapping("/{email}/promover-professor")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Método para promover um usuário a professor")
    public ResponseEntity<PromoverProfessorResponseDto> promoverUsuarioProfessor(@PathVariable String email) {
        PromoverProfessorResponseDto professorPromovido = promoverService.execute(email);
        return ResponseEntity.ok(professorPromovido);
    }

}
