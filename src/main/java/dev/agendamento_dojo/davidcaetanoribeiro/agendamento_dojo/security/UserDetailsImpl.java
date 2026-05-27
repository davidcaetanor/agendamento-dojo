package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.security;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.UsuarioEntity;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private final UsuarioEntity usuario;

    public UserDetailsImpl(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    @Override
    public @NullMarked Collection<? extends GrantedAuthority> getAuthorities() {
        return usuario.getRoleUsers().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public @Nullable String getPassword() {
        return usuario.getSenhaUsuario();
    }

    @Override
    public @NullMarked String getUsername() {
        return usuario.getEmailUsuario();
    }

}
