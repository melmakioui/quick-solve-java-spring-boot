package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.DepartmentDTO;
import com.quicksolve.proyecto.dto.IncidenceDTO;
import com.quicksolve.proyecto.entity.DepartmentLanguage;
import com.quicksolve.proyecto.entity.Incidence;
import com.quicksolve.proyecto.entity.IncidenceStateLanguage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IncidenceMapper {

    IncidenceMapper INSTANCE = Mappers.getMapper(IncidenceMapper.class);

    @Mapping(source = "incidence.id", target = "id")
    @Mapping(source = "incidence.title", target = "title")
    @Mapping(source = "incidence.description", target = "description")
    @Mapping(source = "incidence.email", target = "email")
    @Mapping(source = "department.name", target = "department")
    @Mapping(source = "incidenceStateLanguage.statusName", target = "status")

    IncidenceDTO incidenceDTO (Incidence incidence,
                               DepartmentLanguage department,
                               IncidenceStateLanguage incidenceStateLanguage);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name" ,target = "name")
    DepartmentDTO departmentDTO (DepartmentLanguage departmentLanguage);
}
