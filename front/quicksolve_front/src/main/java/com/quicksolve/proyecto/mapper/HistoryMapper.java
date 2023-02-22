package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.HistoryDTO;
import com.quicksolve.proyecto.entity.History;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HistoryMapper {

    HistoryMapper INSTANCE = Mappers.getMapper(HistoryMapper.class);

    HistoryDTO historyToDTO(History history);
}
