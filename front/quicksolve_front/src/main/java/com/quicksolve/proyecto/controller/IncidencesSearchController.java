package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.service.IncidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IncidencesSearchController {

    @Autowired
    private IncidenceService incidenceService;

    @PostMapping(value = "/incidencias/buscar", produces = "application/json")
    public @ResponseBody List<FullIncidenceDTO> showAllResolvedIncidences(@RequestBody String search){
        return incidenceService.listIncidencesByStateAndSearch(3, search);
    }
}
