package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginInputDto(
        @NotBlank(message = "O campo de email está vazio!") @Email String emailUsuario,
        @NotBlank(message = "O campo de senha está vazio!") String senhaUsuario
) {
}
