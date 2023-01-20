package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.dto.IncidenceDTO;
import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.entity.Incidence;

import java.util.List;


public interface IncidenceService {

    List<FullIncidenceDTO> list();
    FullIncidenceDTO findById(long id);
    void save(FullIncidenceDTO incidenceDepartmentDTO);
    void delete(long id);
    void update(FullIncidenceDTO incidenceDepartmentDTO);
}
