package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_aluno")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "aluno_id")
    private UUID idAluno;


    @Column(name = "data_matricula")
    private LocalDateTime dataMatricula;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "alunoAgendado")
    private List<AgendamentoEntity> agendamentosCadastrados = new ArrayList<>();

}
