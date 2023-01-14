package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.IncidenceDTO;
import com.quicksolve.proyecto.entity.Department;
import com.quicksolve.proyecto.entity.Incidence;
import com.quicksolve.proyecto.service.DepartmentLanguageService;
import com.quicksolve.proyecto.service.DepartmentService;
import com.quicksolve.proyecto.service.IncidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IncidenceController {

    private final IncidenceService incidenceService;
    private final DepartmentService departmentService;

    @Autowired
    public IncidenceController(IncidenceService incidenceService, DepartmentService departmentService) {
        this.incidenceService = incidenceService;
        this.departmentService = departmentService;
    }

    @GetMapping("/incidencia/nueva")
    public String showForm(Model model) {
        model.addAttribute("departments", departmentService.list());
        model.addAttribute("incidence", new Incidence());
        return "incidenceForm";
    }

    @GetMapping("/incidencia/modificar/{id}")
    public String showUpdateForm(@PathVariable long id, @ModelAttribute Incidence incidence, Model model) {
        model.addAttribute("incidenceUpdate", incidenceService.get(id));
        model.addAttribute("isUpdate", true);
        return "incidenceForm";
    }

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

    @PostMapping("/nueva/incidencia")
    public String saveIncidence(@ModelAttribute Incidence incidence) {
        incidenceService.save(incidence);
        return "redirect:/incidencias";
    }

    @PutMapping("/modificar/incidencia")
    public String updateIncidence(@ModelAttribute Incidence incidence, Model model) {
        model.addAttribute("incidence", incidence);
        incidenceService.update(incidence);
        return "test";
    }


}
