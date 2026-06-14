package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AtualizarEnderecoInputDto(
        @NotBlank(message = "O cep é obrigatório.")
        @Pattern(regexp = "\\d{8}", message = "O cep deve conter exatamente 8 números")
        String cep,

        @NotBlank(message = "Informe o numero residencial")
        String numero,

        String complemento
) {
}
