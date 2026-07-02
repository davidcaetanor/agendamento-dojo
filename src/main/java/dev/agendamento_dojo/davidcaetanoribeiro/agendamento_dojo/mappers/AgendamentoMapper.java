package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.mappers;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.output.AgendamentoAulaResponseDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.AgendamentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AgendamentoMapper {

    @Mapping(target = "tituloAula", source = "agendamento.aulaAgendada.tituloAula")
    @Mapping(target = "dataHora", source = "agendamento.aulaAgendada.dataHora")
    @Mapping(target = "dataHoraFim", source = "agendamento.aulaAgendada.dataHoraFim")
    AgendamentoAulaResponseDto toDto(AgendamentoEntity agendamento);
}
