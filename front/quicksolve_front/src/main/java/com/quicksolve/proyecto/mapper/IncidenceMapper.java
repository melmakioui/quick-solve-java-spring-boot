package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.entity.Incidence;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IncidenceMapper {

    IncidenceMapper INSTANCE = Mappers.getMapper(IncidenceMapper.class);

    @Mapping(source = "incidenceState.id", target = "incidenceStateId")
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "space.id", target = "spaceId")
    FullIncidenceDTO incidenceToDTO(Incidence incidence);

    Incidence dtoToIncidence(FullIncidenceDTO incidenceDepartmentDTO);
}
