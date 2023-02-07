package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.ServiceDTO;
import com.quicksolve.proyecto.entity.Service;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ServiceMapper {
    ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);

    ServiceDTO serviceToDTO(Service service);
}
