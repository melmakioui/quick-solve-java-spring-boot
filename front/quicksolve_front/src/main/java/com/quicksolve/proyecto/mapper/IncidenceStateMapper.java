package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.IncidenceStateDTO;
import com.quicksolve.proyecto.entity.IncidenceStateLanguage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IncidenceStateMapper {

    IncidenceStateMapper INSTANCE = Mappers.getMapper(IncidenceStateMapper.class);

    @Mapping(source = "status.id", target = "id")
    @Mapping(source = "statusName", target = "name")
    IncidenceStateDTO toIncidenceStateDTO(IncidenceStateLanguage incidenceStateLanguage);

}
