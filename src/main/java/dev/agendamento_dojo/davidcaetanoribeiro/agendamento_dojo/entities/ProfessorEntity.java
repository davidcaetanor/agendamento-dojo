package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_professor")
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "professor_id")
    private UUID idProfessor;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "professor")
    private List<AulaEntity> listaAulas = new ArrayList<>();
}
