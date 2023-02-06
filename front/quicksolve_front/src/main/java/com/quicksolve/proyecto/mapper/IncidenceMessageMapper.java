package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.IncidenceMessageDTO;
import com.quicksolve.proyecto.entity.IncidenceMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IncidenceMessageMapper {

    IncidenceMessageMapper INSTANCE = Mappers.getMapper(IncidenceMessageMapper.class);

    IncidenceMessageDTO toIncidenceMessageDTO(IncidenceMessage incidenceMessage);

    IncidenceMessage toIncidenceMessage(IncidenceMessageDTO incidenceMessageDTO);

}
