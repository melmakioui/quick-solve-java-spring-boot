package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.dto.IncidenceMessageDTO;
import com.quicksolve.proyecto.entity.IncidenceMessage;

import java.util.List;

public interface IncidenceMessageService {

    List<IncidenceMessageDTO> findAllByIncidenceId(long incidenceId);
    IncidenceMessageDTO findByIdAndIncidenceIdAndUserId(long messageId, long incidenceId, long userId);
    void save(IncidenceMessageDTO incidenceMessageDTO, long incidenceId, long userId);
    void update(IncidenceMessageDTO incidenceMessageDTO, long incidenceId, long messageId, long userId);
    void delete(long messageId,long incidenceId, long userId);
    IncidenceMessageDTO findById(long id);


}
