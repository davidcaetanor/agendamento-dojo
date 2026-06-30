package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.mappers;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input.AulaRequestDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.output.AulaResponseDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.AulaEntity;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.ProfessorEntity;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.services.ProfessorService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AulaMapper {

    @Autowired
    protected ProfessorService professorService;

    @Mapping(target = "idAula", ignore = true)
    @Mapping(target = "professor", source = "idProfessor")
    public abstract AulaEntity toEntity(AulaRequestDto requestDto);

    @Mapping(target = "idProfessor", source = "professor.idProfessor")
    @Mapping(target = "nomeProfessor", source = "professor.usuario.nomeUsuario")
    public abstract AulaResponseDto toDto(AulaEntity aulaEntity);

    protected ProfessorEntity mapProfessorIdEntity(UUID idProfessor) {
        if (idProfessor == null) return null;
        return professorService.buscarEntityProfessorId(idProfessor);
    }
}
