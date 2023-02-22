package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.dto.HistoryDTO;

import java.util.List;

public interface HistoryService {

    void saveHistory(long incidenceId);
    List<HistoryDTO> getHistoryByIncidenceId(long incidenceId);
}
