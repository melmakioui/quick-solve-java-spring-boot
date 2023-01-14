package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.dto.IncidenceDTO;
import com.quicksolve.proyecto.entity.Incidence;

import java.util.List;


public interface IncidenceService {

    List<IncidenceDTO> list();
    Incidence findById(Long id);
    void save(Incidence incidence);
    void delete(Incidence incidence);
    void update(Incidence incidence);
}
