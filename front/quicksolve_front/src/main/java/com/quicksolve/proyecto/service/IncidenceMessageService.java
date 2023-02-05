package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.dto.IncidenceMessageDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncidenceMessageService {

    IncidenceMessageDTO findById(long id);
    List<IncidenceMessageDTO> findAllByIncidenceIdAndUserId(long incidenceId, long userId);
    List<IncidenceMessageDTO> findAllByIncidenceId(long incidenceId);
    void save(IncidenceMessageDTO incidenceMessageDTO, long incidenceId, FullUserDTO fullUserDTO);
    void delete(long incidenceMessageId, long incidenceId, FullUserDTO fullUserDTO);
    void update(IncidenceMessageDTO incidenceMessageDTO, long incidenceId, long incidenceMessageId, FullUserDTO fullUserDTO);
    void verifyOwner (long incidenceMessageId, FullUserDTO fullUserDTO);

}
