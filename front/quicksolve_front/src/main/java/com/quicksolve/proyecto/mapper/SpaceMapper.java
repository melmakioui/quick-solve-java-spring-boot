package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.SpaceDTO;
import com.quicksolve.proyecto.entity.Space;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpaceMapper {
    SpaceMapper INSTANCE = Mappers.getMapper(SpaceMapper.class);

    @Mapping(source = "id", target = "id")
    SpaceDTO departmentDTO (Space space);

    @Mapping(source = "id", target = "id")
    Space space (SpaceDTO spaceDTO);
}
