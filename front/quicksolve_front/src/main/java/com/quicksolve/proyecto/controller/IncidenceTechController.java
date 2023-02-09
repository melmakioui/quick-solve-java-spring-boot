package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.service.implementation.IncidenceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"userlogin"})
public class IncidenceTechController {

    @Autowired
    IncidenceServiceImpl incidenceService;

    @GetMapping("/incidencias/tech")
    public String showIncidencesTech(Model model){
        model.addAttribute("incidences", incidenceService.listByAssignedTech((FullUserDTO) model.getAttribute("userlogin")));
        return "view/incidencesTech";
    }
}
