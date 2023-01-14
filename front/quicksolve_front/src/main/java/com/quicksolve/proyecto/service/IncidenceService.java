package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.dto.IncidenceDTO;
import com.quicksolve.proyecto.entity.Incidence;

import java.util.List;


public interface IncidenceService {

    List<IncidenceDTO> list();
    IncidenceDTO get(Long id);
    void save(Incidence incidence);
    boolean delete(Incidence incidence);
    boolean update(Incidence incidence);
}
