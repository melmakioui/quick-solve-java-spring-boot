package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.entity.Incidence;
import com.quicksolve.proyecto.entity.IncidenceState;
import com.quicksolve.proyecto.service.IncidenceService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IncidenceMapper {

    IncidenceMapper INSTANCE = Mappers.getMapper(IncidenceMapper.class);

    @Mapping(source = "incidenceState.id", target = "incidenceStateId")
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "space.id", target = "spaceId")
    FullIncidenceDTO incidenceDTO (Incidence incidence);


    Incidence DTOtoIncidence(FullIncidenceDTO incidenceDepartmentDTO);
}
