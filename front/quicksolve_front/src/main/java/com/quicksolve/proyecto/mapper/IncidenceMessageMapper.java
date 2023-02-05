package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.IncidenceMessageDTO;
import com.quicksolve.proyecto.entity.IncidenceMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IncidenceMessageMapper {

    IncidenceMessageMapper INSTANCE = Mappers.getMapper(IncidenceMessageMapper.class);

    @Mapping(source = "user.user.username", target = "userNameUser")
    @Mapping(source = "tech.tech.username", target = "userNameTech")
    IncidenceMessageDTO toIncidenceMessageDTO(IncidenceMessage incidenceMessage);

    IncidenceMessage toIncidenceMessage(IncidenceMessageDTO incidenceMessageDTO);

}
