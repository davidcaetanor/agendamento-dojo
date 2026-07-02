package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.controllers;


import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input.AtualizarEnderecoInputDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.services.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/alunos")
@Tag(name = "Rota de Alunos", description = "Rota de visualização e Manipulação de Alunos")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PutMapping("/me/endereco")
    @PreAuthorize("hasRole('ALUNO')")
    @Operation(summary = "Atualizacao de Endereco",
            description = "Permite que o aluno, logado, altere seu próprio endereco, informando o cep, numero e complemento")
    public ResponseEntity<Void> atualizarMeuEndereco(@RequestBody @Valid AtualizarEnderecoInputDto dto) {
        alunoService.atualizarMeuEndereco(dto);

        return ResponseEntity.noContent().build();
    }

}
