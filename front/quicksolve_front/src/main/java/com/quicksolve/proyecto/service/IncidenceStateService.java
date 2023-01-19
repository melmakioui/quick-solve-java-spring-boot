package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.dto.IncidenceStateDTO;

import java.util.List;

public interface IncidenceStateService {

    List<IncidenceStateDTO> list();
    IncidenceStateDTO findById(Long id);
}
