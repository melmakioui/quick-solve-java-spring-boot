package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.AdvantageDTO;
import com.quicksolve.proyecto.entity.Advantage;
import com.quicksolve.proyecto.entity.AdvantageLanguage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdvantageMapper {
    AdvantageMapper INSTANCE = Mappers.getMapper(AdvantageMapper.class);

    AdvantageDTO advantageToDTO(AdvantageLanguage advantage);
}
