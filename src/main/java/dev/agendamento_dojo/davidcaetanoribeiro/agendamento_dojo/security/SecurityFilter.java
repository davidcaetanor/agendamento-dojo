package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.UsuarioEntity;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.repositories.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    private final HandlerExceptionResolver exceptionResolver;

    public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository, @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recuperarToken(request);

        try {
            if (token != null) {
                String email = tokenService.validarToken(token);

                if (!email.isBlank()) {
                    UsuarioEntity usuario = usuarioRepository.findUsuarioEntitiesByEmailUsuario(email)
                            .orElseThrow(() -> new UsernameNotFoundException("Não foi encontrado nenhum usuario com este email!"));

                    UserDetails userDetails = new UserDetailsImpl(usuario);

                    var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request, response);
        } catch (JWTVerificationException e) {
            exceptionResolver.resolveException(request, response, null, e);
        }
    }

    private String recuperarToken(HttpServletRequest httpServletRequest) {
        var authHeader = httpServletRequest.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
