package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.dto.IncidenceMessageDTO;
import com.quicksolve.proyecto.service.IncidenceFileService;
import com.quicksolve.proyecto.service.IncidenceMessageService;
import com.quicksolve.proyecto.service.IncidenceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Collections;
import java.util.List;

@Controller
@SessionAttributes({"userlogin"})
public class MessageController {

    @Autowired
    private IncidenceMessageService incidenceMessageService;
    @Autowired
    private IncidenceService incidenceService;
    @Autowired
    private IncidenceFileService incidenceFileService;

    @PostMapping("/guardar/mensaje/{incidenceId}")
    public String saveMessage(@PathVariable long incidenceId, @Valid IncidenceMessageDTO incidenceMessageDTO, BindingResult result, Model model) {
        FullUserDTO user = (FullUserDTO) model.getAttribute("userlogin");
        incidenceMessageService.save(incidenceMessageDTO, incidenceId, user);
        return "redirect:/incidencia/" + incidenceId;
    }


    @GetMapping("/borrar/mensaje/{incidenceId}/{messageId}")
    public String deleteMessage(@PathVariable long incidenceId, @PathVariable long messageId,Model model) {
        FullUserDTO user = (FullUserDTO) model.getAttribute("userlogin");
        incidenceMessageService.delete(messageId, incidenceId, user);
        return "redirect:/incidencia/" + incidenceId;
    }


    @GetMapping("/editar/mensaje/{incidenceId}/{messageId}")
    public String updateMessage(@PathVariable long incidenceId, @PathVariable long messageId, Model model) {

        FullUserDTO user = (FullUserDTO) model.getAttribute("userlogin");
        incidenceMessageService.verifyOwner(messageId,user);

        FullIncidenceDTO incidenceDTO = incidenceService.findById(incidenceId);

        incidenceDTO.setIncidenceFiles(incidenceFileService.findAllByIncidenceId(incidenceId));
        List<IncidenceMessageDTO> incidenceMessageDTOS = incidenceMessageService.findAllByIncidenceId(incidenceId);
        Collections.reverse(incidenceMessageDTOS);
        incidenceDTO.setMessages(incidenceMessageDTOS);

        model.addAttribute("message", incidenceMessageService.findById(messageId));
        model.addAttribute("isUpdateMessage", true);
        model.addAttribute("incidence", incidenceDTO);
        return "view/incidence";
    }

    @PostMapping("/editar/mensaje/{incidenceId}/{messageId}")
    public String updateMessage(@PathVariable long incidenceId, @PathVariable long messageId, @Valid IncidenceMessageDTO incidenceMessageDTO, BindingResult result, Model model) {
        FullUserDTO user = (FullUserDTO) model.getAttribute("userlogin");

        if (result.hasErrors()){
            return "redirect:/editar/mensaje/" + incidenceId + "/" + messageId;
        }

        incidenceMessageService.update(incidenceMessageDTO, incidenceId, messageId, user);
        return "redirect:/incidencia/" + incidenceId;
    }
}
