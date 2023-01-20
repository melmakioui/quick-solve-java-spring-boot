package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.IncidenceDTO;
import com.quicksolve.proyecto.entity.Incidence;
import com.quicksolve.proyecto.service.DepartmentService;
import com.quicksolve.proyecto.service.IncidenceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes({"userlogin"})
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
        return "view/incidenceForm";
    }

    @GetMapping("/incidencia/modificar/{id}")
    public String showUpdateForm(@PathVariable long id, @ModelAttribute Incidence incidence, Model model) {
        model.addAttribute("departments", departmentService.list());
        model.addAttribute("incidence", incidenceService.findById(id));
        return "view/incidenceFormUpdate";
    }

    @GetMapping("/incidencias")
    public String showIncidences(Model model) {
        List<IncidenceDTO> incidenceDTOS = incidenceService.list();
        model.addAttribute("incidences", incidenceDTOS);
        return "view/incidences";
    }

/*    @GetMapping("/incidencia/{id}")
    public String showIncidence(@PathVariable long id, Model model) {
        model.addAttribute("incidence", incidenceService.findByIdDTO(id));
        return "view/incidence";
    }*/

    @GetMapping("/incidencia/eliminar/{id}")
    public String deleteIncidence(@PathVariable long id) {
        incidenceService.delete(id);
        return "redirect:/incidencias";
    }

    @PostMapping("/nueva/incidencia")
    public String saveIncidence(@Valid Incidence incidence, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.list());
            model.addAttribute("incidence", incidence);
            return "view/incidenceForm";
        }

        incidenceService.save(incidence);

        return "redirect:/incidencias";
    }

    @PostMapping("/modificar/incidencia/{id}")
    public String updateIncidence(@PathVariable("id") long id, @Valid Incidence incidence, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.list());
            model.addAttribute("incidence", incidence);
            return "view/incidenceFormUpdate";
        }

        incidenceService.update(incidence,id);
        return "redirect:/incidencias";
    }
}
