package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.mappers;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input.AtualizarEnderecoInputDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input.EnderecoViaCepDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.vo.EnderecoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EnderecoMapper {

    @Mapping(target = "cep", source = "viaCep.cep", qualifiedByName = "limparCep")
    @Mapping(target = "logradouro", source = "viaCep.logradouro")
    @Mapping(target = "complemento", source = "input.complemento")
    @Mapping(target = "unidade", source = "viaCep.unidade")
    @Mapping(target = "bairro", source = "viaCep.bairro")
    @Mapping(target = "localidade", source = "viaCep.localidade")
    @Mapping(target = "uf", source = "viaCep.uf")
    @Mapping(target = "estado", source = "viaCep.estado")
    @Mapping(target = "regiao", source = "viaCep.regiao")
    @Mapping(target = "numero", source = "input.numero")
    EnderecoVO toVO(EnderecoViaCepDto viaCep, AtualizarEnderecoInputDto input);

    @Named("limparCep")
    default String limparCep(String cep) {
        if (cep == null) return null;
        return cep.replace("-", "");
    }

}
