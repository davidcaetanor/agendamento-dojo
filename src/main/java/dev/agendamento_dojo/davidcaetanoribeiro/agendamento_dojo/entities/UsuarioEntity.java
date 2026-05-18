package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.enums.RoleUser;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "usuario_id")
    private UUID idUsuario;

    @Column(name = "cpf_usuario", length = 11, unique = true, nullable = false)
    private String cpfUsuario;

    @Column(name = "nome_usuario", nullable = false)
    private String nomeUsuario;

    @Column(name = "email_usuario", unique = true)
    private String emailUsuario;

    @Column(name = "senha_usuario", nullable = false)
    private String senhaUsuario;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role_user")
    private Set<RoleUser> roleUsers = new HashSet<>();

}
