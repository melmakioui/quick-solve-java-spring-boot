package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.dto.FullIncidenceDTO;

import java.util.List;


public interface IncidenceService {

    List<FullIncidenceDTO> list();
    List<FullIncidenceDTO> list(FullUserDTO userDTO);
    List<FullIncidenceDTO> list(Long departmentId, Long spaceId, String startDate, FullUserDTO userDTO);
    List<FullIncidenceDTO> listByAssignedTech(FullUserDTO userDTO);
    FullIncidenceDTO findById(long id);
    void save(FullIncidenceDTO incidenceDepartmentDTO);
    void save(FullIncidenceDTO incidenceDepartmentDTO, FullUserDTO userDTO);

    List<FullIncidenceDTO> listIncidencesByStateAndSearch(long id, String search);
    void delete(long id);
    void cancel(long id);
    void update(FullIncidenceDTO incidenceDepartmentDTO);
    FullIncidenceDTO getLastIncidence();
    FullIncidenceDTO getLastUpdatedIncidence();
    FullIncidenceDTO findIncidenceByIdAndUserId(long incidenceId, FullUserDTO userDTO);
    void changeState(long incidenceId, long stateId);
}
