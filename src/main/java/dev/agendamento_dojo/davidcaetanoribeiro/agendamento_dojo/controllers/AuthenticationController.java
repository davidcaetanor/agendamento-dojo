package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.controllers;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input.UsuarioLoginInputDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input.UsuarioRegisterInputDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.output.UsuarioLoginOutputDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.output.UsuarioRegisterOutputDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioRegisterOutputDto> registrarUsuario(@Valid @RequestBody UsuarioRegisterInputDto registerDto) {
        authenticationService.registrar(registerDto);
        var outputDto = new UsuarioRegisterOutputDto(registerDto.emailUsuario(), registerDto.nomeUsuario());
        return ResponseEntity.status(HttpStatus.CREATED).body(outputDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioLoginOutputDto> login(@Valid @RequestBody UsuarioLoginInputDto loginDto) {
        String token = authenticationService.autenticar(loginDto);
        var outputDto = new UsuarioLoginOutputDto(token, "Bearer");
        return ResponseEntity.status(HttpStatus.OK).body(outputDto);
    }

}
