package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.*;
import com.quicksolve.proyecto.entity.type.UserType;
import com.quicksolve.proyecto.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
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
    @Autowired
    private IncidenceFileService incidenceFileService;
    @Autowired
    private IncidenceMessageService incidenceMessageService;



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
        model.addAttribute("isNewIncidence", true);

        return "view/incidenceForm";
    }

    @GetMapping("/incidencia/modificar/{id}")
    public String showUpdateForm(@PathVariable long id, Model model) {
        model.addAttribute("departments", departmentService.list());
        model.addAttribute("spaces", spaceService.list());
        FullIncidenceDTO incidence = incidenceService.findById(id);
        incidence.setIncidenceFiles(incidenceFileService.findAllByIncidenceId(id));
        model.addAttribute("incidence", incidence);
        model.addAttribute("isNewIncidence", false);
        return "view/incidenceForm";
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
            incidence.setIncidenceFiles(incidenceFileService.findAllByIncidenceId(incidence.getId()));
        });

        System.out.println(incidenceDTOS);
        model.addAttribute("departments", departmentService.list());
        model.addAttribute("spaces", spaceService.list());
        model.addAttribute("incidences", incidenceDTOS);
        model.addAttribute("status", incidenceStateService.list());
        return "view/incidences";
    }

    @GetMapping("/incidencia/{incidenceId}")
    public String showIncidence(@PathVariable long incidenceId, Model model) {

        FullUserDTO user = (FullUserDTO) model.getAttribute("userlogin");
        FullIncidenceDTO incidenceDTO = null;
        System.out.println(user);
        if (user.getType() == UserType.USER) {
             incidenceDTO = incidenceService.findIncidenceByIdAndUserId(incidenceId,  user.getId());
        }else {
            incidenceDTO = incidenceService.findIncidenceByIdAndUserTechId(incidenceId,  user.getId());
        }

        //Monta el dto con los datos de la incidencia
        incidenceDTO.setIncidenceFiles(incidenceFileService.findAllByIncidenceId(incidenceId));
        List<IncidenceMessageDTO> incidenceMessageDTOS = incidenceMessageService.findAllByIncidenceId(incidenceId);
        Collections.reverse(incidenceMessageDTOS);
        incidenceDTO.setMessages(incidenceMessageDTOS);


        model.addAttribute("incidence", incidenceDTO);
        model.addAttribute("newMessage", new IncidenceMessageDTO());
        return "view/incidence";
    }

    @GetMapping("/incidencia/cancelar/{id}")
    public String cancelIncidence(@PathVariable long id) {
        incidenceService.cancel(id);
        return "redirect:/incidencias";
    }

    @PostMapping("/public/nueva/incidencia")
    public String saveNoUserIncidence(@Valid FullIncidenceDTO newIncidence, BindingResult bindingResult, Model model, @RequestParam("files") MultipartFile[] files) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.list());
            model.addAttribute("spaces", spaceService.list());
            model.addAttribute("incidence", newIncidence);
            model.addAttribute("isNewIncidence", true);

            return "view/incidenceNoLoginForm";
        }

        incidenceFileService.validateFiles(files);
        incidenceService.save(newIncidence);
        FullIncidenceDTO fullIncidenceDTO = incidenceService.getLastIncidence();
        incidenceFileService.saveIncidenceFiles(files, fullIncidenceDTO);

        return "redirect:/";
    }

    @PostMapping("/nueva/incidencia")
    public String saveIncidence(@Valid FullIncidenceDTO incidenceDepartmentDTO, BindingResult bindingResult, Model model, @RequestParam("files") MultipartFile[] files) {

        if (model.getAttribute("userlogin") == null)
            throw new IllegalStateException("No user logged in");

         bindingResult = excludeEmailFormValidationForUsers(bindingResult, incidenceDepartmentDTO, model);

        if (bindingResult.hasErrors()) {
            model.addAttribute("spaces", spaceService.list());
            model.addAttribute("departments", departmentService.list());
            model.addAttribute("incidence", incidenceDepartmentDTO);
            model.addAttribute("isNewIncidence", true);

            return "view/incidenceForm";
        }

        incidenceFileService.validateFiles(files);
        incidenceService.save(incidenceDepartmentDTO,
                (FullUserDTO) model.getAttribute("userlogin"));
        FullIncidenceDTO fullIncidenceDTO = incidenceService.getLastIncidence();
        incidenceFileService.saveIncidenceFiles(files, fullIncidenceDTO);
        return "redirect:/incidencias";
    }

    @PostMapping("/modificar/incidencia/{id}")
    public String updateIncidence(@Valid FullIncidenceDTO fullIncidenceDTO, BindingResult bindingResult, Model model, @RequestParam("files") MultipartFile[] files) {

        if (model.getAttribute("userlogin") == null)
            throw new IllegalStateException("No user logged in");

        bindingResult = excludeEmailFormValidationForUsers(bindingResult, fullIncidenceDTO, model);

        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", spaceService.list());
            model.addAttribute("departments", departmentService.list());
            model.addAttribute("incidence", fullIncidenceDTO);
            model.addAttribute("isNewIncidence", false);
            return "view/incidenceForm";
        }

        incidenceFileService.validateFiles(files);
        incidenceService.update(fullIncidenceDTO);
        incidenceFileService.saveIncidenceFiles(files, fullIncidenceDTO);
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
