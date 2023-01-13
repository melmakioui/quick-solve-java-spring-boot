package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.IncidenceDTO;
import com.quicksolve.proyecto.entity.Incidence;
import com.quicksolve.proyecto.service.DepartmentLanguageService;
import com.quicksolve.proyecto.service.IncidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class IncidenceController {

    @Autowired
    private IncidenceService incidenceService;

    @GetMapping("/incidencia/nueva")
    public String showForm(Model model) {
        model.addAttribute("incidence", new Incidence());
        return "incidenceForm";
    }

    //CRUD

    @GetMapping("/incidencias")
    public String getIncidenceList(Model model) {
        List<IncidenceDTO> incidenceDTOS = incidenceService.list();
        model.addAttribute("incidences", incidenceDTOS);
        return "incidences";
    }

    @GetMapping("/incidencia/{id}")
    public String getIncidence(@PathVariable long id, Model model) {

        IncidenceDTO incidenceDTO = incidenceService.get(id);

        if (incidenceDTO != null) {
            model.addAttribute("incidence", incidenceDTO);
            return "incidence";
        }
        return "redirect:/incidencias";
    }

    @PostMapping("/incidencias")
    public String saveIncidence(@ModelAttribute IncidenceDTO incidenceDTO) {
        boolean saved = incidenceService.save(incidenceDTO);
        if (saved) return "redirect:/incidences";
        return "incidences";
    }

}
