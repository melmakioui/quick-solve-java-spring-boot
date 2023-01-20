package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.DepartmentDTO;
import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.dto.IncidenceStateDTO;
import com.quicksolve.proyecto.dto.SpaceDTO;
import com.quicksolve.proyecto.entity.Incidence;
import com.quicksolve.proyecto.service.DepartmentService;
import com.quicksolve.proyecto.service.IncidenceService;
import com.quicksolve.proyecto.service.IncidenceStateService;
import com.quicksolve.proyecto.service.SpaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IncidenceController {

    private final IncidenceService incidenceService;
    private final DepartmentService departmentService;
    private final SpaceService spaceService;
    private final IncidenceStateService incidenceStateService;

    @Autowired
    public IncidenceController(IncidenceService incidenceService, DepartmentService departmentService, SpaceService spaceService, IncidenceStateService incidenceStateService) {
        this.incidenceService = incidenceService;
        this.departmentService = departmentService;
        this.spaceService = spaceService;
        this.incidenceStateService = incidenceStateService;
    }

    @GetMapping("/incidencia/nueva")
    public String showForm(Model model) {

        FullIncidenceDTO fullIncidenceDTO = new FullIncidenceDTO();
        fullIncidenceDTO.setDepartment(new DepartmentDTO());
        fullIncidenceDTO.setSpace(new SpaceDTO());

        List<DepartmentDTO> departments = departmentService.list();
        List<SpaceDTO> spaces = spaceService.list();

        model.addAttribute("spaces", spaces);
        model.addAttribute("departments", departments);
        model.addAttribute("incidence", fullIncidenceDTO);

        return "view/incidenceForm";
    }

    @GetMapping("/incidencia/modificar/{id}")
    public String showUpdateForm(@PathVariable long id, Model model) {
        model.addAttribute("departments", departmentService.list());
        model.addAttribute("spaces", spaceService.list());
        model.addAttribute("incidence", incidenceService.findById(id));
        return "view/incidenceFormUpdate";
    }

    @GetMapping("/incidencias")
    public String showIncidences(Model model) {
        List<FullIncidenceDTO> incidenceDTOS = incidenceService.list();

        incidenceDTOS.forEach(incidence -> {
            incidence.setDepartment(departmentService.findById(incidence.getDepartmentId()));
            incidence.setSpace(spaceService.findById(incidence.getSpaceId()));
            incidence.setIncidenceState(incidenceStateService.findById(incidence.getIncidenceStateId()));
        });

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
    public String saveIncidence(@Valid FullIncidenceDTO incidenceDepartmentDTO, BindingResult bindingResult, Model model) {

/*
        if (bindingResult.hasErrors()) {
            model.addAttribute("spaces", spaceService.list());
            model.addAttribute("departments", departmentService.list());
            model.addAttribute("incidence", incidenceDepartmentDTO);
            return "view/incidenceForm";
        }
*/
        System.out.println(incidenceDepartmentDTO);
        incidenceService.save(incidenceDepartmentDTO);
        return "redirect:/incidencias";
    }

    @PostMapping("/modificar/incidencia/{id}")
    public String updateIncidence(@Valid FullIncidenceDTO fullIncidenceDTO, BindingResult bindingResult, Model model) {
/*
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", spaceService.list());
            model.addAttribute("departments", departmentService.list());
            model.addAttribute("incidence", fullIncidenceDTO);
            return "view/incidenceFormUpdate";
        }
*/
        System.out.println(fullIncidenceDTO);
        incidenceService.update(fullIncidenceDTO);
        return "redirect:/incidencias";
    }
}
