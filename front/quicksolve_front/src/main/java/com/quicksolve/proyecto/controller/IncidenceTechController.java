package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.service.implementation.HistoryServiceImpl;
import com.quicksolve.proyecto.service.implementation.IncidenceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"userlogin"})
public class IncidenceTechController {

    @Autowired
    IncidenceServiceImpl incidenceService;

    @Autowired
    HistoryServiceImpl historyService;

    @GetMapping("/incidencias/tech")
    public String showIncidencesTech(Model model){
        model.addAttribute("incidences", incidenceService.listByAssignedTech((FullUserDTO) model.getAttribute("userlogin")));
        return "view/incidencesTech";
    }

    @GetMapping("/incidencia/tech/cancelar/{id}")
    public String cancelIncidenceByTechnician(@PathVariable long id) {
        incidenceService.cancel(id);
        historyService.saveHistory(id);
        return "redirect:/incidencias/tech";
    }
}
