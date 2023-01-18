package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.DepartmentDTO;
import com.quicksolve.proyecto.entity.DepartmentLanguage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name" ,target = "name")
    DepartmentDTO departmentDTO (DepartmentLanguage departmentLanguage);
}
