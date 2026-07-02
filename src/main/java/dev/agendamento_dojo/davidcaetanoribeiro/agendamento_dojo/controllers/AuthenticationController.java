package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.controllers;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input.UsuarioLoginRequestDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input.UsuarioRegisterRequestDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.output.UsuarioLoginResponseDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.output.UsuarioRegisterResponseDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Rota de Autenticacao", description = "Registro e autenticacao do usuario")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    @Operation(summary = "Local de Registro do usuario",
            description = "O usuario informa seus dados e após o registro recebe um JSON com nome e email")
    public ResponseEntity<UsuarioRegisterResponseDto> registrarUsuario(@Valid @RequestBody UsuarioRegisterRequestDto registerDto) {
        authenticationService.registrar(registerDto);
        var outputDto = new UsuarioRegisterResponseDto(registerDto.emailUsuario(), registerDto.nomeUsuario());
        return ResponseEntity.status(HttpStatus.CREATED).body(outputDto);
    }

    @PostMapping("/login")
    @Operation(summary = "Local de Autenticacao do usuario",
            description = "O usuario efetua login, mediante aos dados válidos ele recebe um JSON com o token e o tipo dele")
    public ResponseEntity<UsuarioLoginResponseDto> login(@Valid @RequestBody UsuarioLoginRequestDto loginDto) {
        String token = authenticationService.autenticar(loginDto);
        var outputDto = new UsuarioLoginResponseDto(token, "Bearer");
        return ResponseEntity.status(HttpStatus.OK).body(outputDto);
    }

}
