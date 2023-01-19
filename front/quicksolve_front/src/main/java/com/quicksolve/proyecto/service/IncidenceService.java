package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.dto.IncidenceDTO;
import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.entity.Incidence;

import java.util.List;


public interface IncidenceService {

    List<FullIncidenceDTO> list();
    IncidenceDTO findByIdDTO(long id);
    Incidence findById(long id);
    void saveTest(FullIncidenceDTO incidenceDepartmentDTO);
    void save(Incidence incidence);
    void delete(long id);
    void update(Incidence incidence,long id);
}
