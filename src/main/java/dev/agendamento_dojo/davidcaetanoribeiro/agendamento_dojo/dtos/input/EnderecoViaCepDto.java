package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input;


public record EnderecoViaCepDto(
        String cep,

        String logradouro,

        String complemento,

        String unidade,

        String bairro,

        String localidade,

        String uf,

        String estado,

        String regiao,

        Boolean erro
) {

}
