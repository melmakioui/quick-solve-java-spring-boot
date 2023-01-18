package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.SpaceDTO;
import com.quicksolve.proyecto.entity.Space;
import com.quicksolve.proyecto.entity.SpaceLanguage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpaceMapper {
    SpaceMapper INSTANCE = Mappers.getMapper(SpaceMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    SpaceDTO spaceDTO (SpaceLanguage spaceLanguage);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    SpaceLanguage space (SpaceDTO spaceDTO);
}
