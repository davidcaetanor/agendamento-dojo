package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.services;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input.UsuarioLoginInputDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input.UsuarioRegisterInputDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.UsuarioEntity;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.exceptions.RegistrarUsuarioException;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.mappers.UsuarioRegisterMapper;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.repositories.UsuarioRepository;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.security.TokenService;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioRegisterMapper usuarioRegisterMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationService(UsuarioRepository usuarioRepository, UsuarioRegisterMapper usuarioRegisterMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioRegisterMapper = usuarioRegisterMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Transactional
    public void registrar(UsuarioRegisterInputDto registerDto) {
        if (usuarioRepository.existsByEmailUsuario(registerDto.emailUsuario())) {
            throw new RegistrarUsuarioException("E!");
        }

        UsuarioEntity usuarioNovo = usuarioRegisterMapper.toEntity(registerDto);

        String senhaBCrypt = passwordEncoder.encode(registerDto.senhaUsuario());

        usuarioNovo.setSenhaUsuario(senhaBCrypt);

        usuarioRepository.save(usuarioNovo);
    }

    public String autenticar(UsuarioLoginInputDto loginDto) {
        var userPass = new UsernamePasswordAuthenticationToken(loginDto.emailUsuario(), loginDto.senhaUsuario());
        var auth = this.authenticationManager.authenticate(userPass);

        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

        assert userDetails != null;

        return tokenService.gerarToken(userDetails.getUsername());
    }

}
