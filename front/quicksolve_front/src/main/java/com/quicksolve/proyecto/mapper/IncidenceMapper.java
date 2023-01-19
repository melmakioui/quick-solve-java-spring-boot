package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.entity.Incidence;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IncidenceMapper {

    IncidenceMapper INSTANCE = Mappers.getMapper(IncidenceMapper.class);

    FullIncidenceDTO incidenceDTO (Incidence incidence);
    Incidence DTOtoIncidence(FullIncidenceDTO incidenceDepartmentDTO);
}
