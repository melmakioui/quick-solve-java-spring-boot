package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.dto.IncidenceDTO;

import java.util.List;
import java.util.Set;


public interface IncidenceService {

    List<IncidenceDTO> list();
    IncidenceDTO get(Long id);
    boolean save(IncidenceDTO incidenceDTO);
    boolean delete(IncidenceDTO incidenceDTO);
    boolean update(IncidenceDTO incidenceDTO);
}
