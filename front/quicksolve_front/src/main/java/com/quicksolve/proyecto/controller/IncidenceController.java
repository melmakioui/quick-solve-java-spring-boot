package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.*;
import com.quicksolve.proyecto.service.DepartmentService;
import com.quicksolve.proyecto.service.IncidenceService;
import com.quicksolve.proyecto.service.IncidenceStateService;
import com.quicksolve.proyecto.service.SpaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes({"userlogin"})
public class IncidenceController {

    @Autowired
    private  IncidenceService incidenceService;
    @Autowired
    private  DepartmentService departmentService;
    @Autowired
    private  SpaceService spaceService;
    @Autowired
    private  IncidenceStateService incidenceStateService;


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

    @GetMapping("/public/nueva")
    public String showNoUserForm(Model model) {
        FullIncidenceDTO fullIncidenceDTO = new FullIncidenceDTO();
        fullIncidenceDTO.setDepartment(new DepartmentDTO());
        fullIncidenceDTO.setSpace(new SpaceDTO());

        model.addAttribute("departments", departmentService.list());
        model.addAttribute("spaces", spaceService.list());
        model.addAttribute("incidence", new FullIncidenceDTO());
        return "view/incidenceNoLoginForm";
    }

    @GetMapping("/incidencias")
    public String showIncidences(Model model) {
        List<FullIncidenceDTO> incidenceDTOS = incidenceService.list((FullUserDTO) model.getAttribute("userlogin"));

        incidenceDTOS.forEach(incidence -> {
            incidence.setDepartment(departmentService.findById(incidence.getDepartmentId()));
            incidence.setSpace(spaceService.findById(incidence.getSpaceId()));
            incidence.setIncidenceState(incidenceStateService.findById(incidence.getIncidenceStateId()));
        });

        model.addAttribute("incidences", incidenceDTOS);
        model.addAttribute("status", incidenceStateService.list());
        return "view/incidences";
    }

    @GetMapping("/incidencia/eliminar/{id}")
    public String deleteIncidence(@PathVariable long id) {
        incidenceService.delete(id);
        return "redirect:/incidencias";
    }

    @PostMapping("/public/nueva/incidencia")
    public String saveNoUserIncidence(@Valid FullIncidenceDTO incidenceDepartmentDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.list());
            model.addAttribute("spaces", spaceService.list());
            model.addAttribute("incidence", incidenceDepartmentDTO);
            return "view/incidenceNoLoginForm";
        }

        incidenceService.save(incidenceDepartmentDTO);
        return "redirect:/incidencias";
    }

    @PostMapping("/nueva/incidencia")
    public String saveIncidence(@Valid FullIncidenceDTO incidenceDepartmentDTO, BindingResult bindingResult, Model model) {

        if (model.getAttribute("userlogin") == null)
            throw new IllegalStateException("No user logged in");

         bindingResult = excludeEmailFormValidationForUsers(bindingResult, incidenceDepartmentDTO, model);

        if (bindingResult.hasErrors()) {
            model.addAttribute("spaces", spaceService.list());
            model.addAttribute("departments", departmentService.list());
            model.addAttribute("incidence", incidenceDepartmentDTO);
            return "view/incidenceForm";
        }

        incidenceService.save(incidenceDepartmentDTO,
                (FullUserDTO) model.getAttribute("userlogin"));

        return "redirect:/incidencias";
    }

    @PostMapping("/modificar/incidencia/{id}")
    public String updateIncidence(@Valid FullIncidenceDTO fullIncidenceDTO, BindingResult bindingResult, Model model) {

        if (model.getAttribute("userlogin") == null)
            throw new IllegalStateException("No user logged in");

        bindingResult = excludeEmailFormValidationForUsers(bindingResult, fullIncidenceDTO, model);
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", spaceService.list());
            model.addAttribute("departments", departmentService.list());
            model.addAttribute("incidence", fullIncidenceDTO);
            return "view/incidenceFormUpdate";
        }

        incidenceService.update(fullIncidenceDTO);
        return "redirect:/incidencias";
    }

    private BindingResult excludeEmailFormValidationForUsers(BindingResult bindingResult, FullIncidenceDTO fullIncidenceDTO, Model model){
        if (model.getAttribute("userlogin") instanceof FullUserDTO) {
            List<FieldError> errorsToKeep = bindingResult.getFieldErrors().stream()
                    .filter(error -> !error.getField().equals("email"))
                    .toList();

            bindingResult = new BeanPropertyBindingResult(fullIncidenceDTO, "incidenceDepartmentDTO");

            for (FieldError fieldError : errorsToKeep) {
                bindingResult.addError(fieldError);
            }
        }
        return bindingResult;
    }
}
