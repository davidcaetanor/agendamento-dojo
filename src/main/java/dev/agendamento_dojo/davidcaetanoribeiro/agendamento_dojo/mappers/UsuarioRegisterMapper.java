package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.mappers;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input.UsuarioRegisterRequestDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioRegisterMapper {

    UsuarioEntity toEntity(UsuarioRegisterRequestDto inputDto);
}
