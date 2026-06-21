package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record UsuarioRegisterRequestDto(
        @CPF(message = "Insira um CPF válido, sem pontuacao! ") @NotBlank(message = "O campo de CPF está vazio!") String cpfUsuario,
        @NotBlank(message = "O Nome do usuario está vazio!") String nomeUsuario,
        @Email(message = "Informe um email válido!") @NotBlank(message = "O email do usuário está vazio!") String emailUsuario,
        @NotBlank(message = "A senha do usuario está vazia!") String senhaUsuario
) {
}
