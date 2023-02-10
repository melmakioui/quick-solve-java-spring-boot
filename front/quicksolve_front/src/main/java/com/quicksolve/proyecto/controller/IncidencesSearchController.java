package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.service.IncidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IncidencesSearchController {

    @Autowired
    private IncidenceService incidenceService;

    @GetMapping("/incidencias/buscar")
    public List<FullIncidenceDTO> showAllResolvedIncidences(){
        String search = "fewfew";
        return incidenceService.listIncidencesByState(3, search);
    }
}
