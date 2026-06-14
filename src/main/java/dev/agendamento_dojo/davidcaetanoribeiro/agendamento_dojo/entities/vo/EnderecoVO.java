package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoVO {

    @Column(name = "endereco_cep")
    private String cep;

    @Column(name = "endereco_logradouro")
    private String logradouro;

    @Column(name = "endereco_numero")
    private String numero;

    @Column(name = "endereco_complemento")
    private String complemento;

    @Column(name = "endereco_unidade")
    private String unidade;

    @Column(name = "endereco_bairro")
    private String bairro;

    @Column(name = "endereco_localidade")
    private String localidade;

    @Column(name = "endereco_uf")
    private String uf;

    @Column(name = "endereco_estado")
    private String estado;

    @Column(name = "endereco_regiao")
    private String regiao;

}
