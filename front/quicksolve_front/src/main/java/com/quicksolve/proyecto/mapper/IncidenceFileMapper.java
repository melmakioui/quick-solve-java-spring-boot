package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.FileDTO;
import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.entity.Incidence;
import com.quicksolve.proyecto.entity.IncidenceFiles;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IncidenceFileMapper {

    IncidenceFileMapper INSTANCE = Mappers.getMapper(IncidenceFileMapper.class);

    FileDTO fileToDTO(IncidenceFiles file);

}
