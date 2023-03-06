package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.dto.IncidenceMessageDTO;
import com.quicksolve.proyecto.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;


@Controller
@SessionAttributes({"userlogin"})
public class MessageController {

    @Autowired
    private IncidenceMessageService messageService;

    @Autowired
    private IncidenceService incidenceService;

    @Autowired
    private IncidenceFileService incidenceFileService;

    @Autowired
    private UserIncidenceService userIncidenceService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @PostMapping("/guardar/mensaje/{incidenceId}")
    public String saveMessage(@PathVariable long incidenceId, @Valid IncidenceMessageDTO incidenceMessageDTO, BindingResult result, Model model) {

        FullUserDTO user = (FullUserDTO) model.getAttribute("userlogin");

        if (result.hasErrors()) {
            throw new RuntimeException("Error al guardar el mensaje");
        }

        FullUserDTO owner = userService.getUserBy(userIncidenceService.findByIncidenceId(incidenceId).getUser().getId());
        FullIncidenceDTO incidence = incidenceService.findById(incidenceId);

        if (user != null && user.getId() != owner.getId()) {
            emailService.sendEmail(owner.getEmail(), incidence.getTitle());
        }

        messageService.save(incidenceMessageDTO, incidenceId, user.getId());
        return "redirect:/incidencia/" + incidenceId;
    }

    @GetMapping("/borrar/mensaje/{incidenceId}/{messageId}")
    public String deleteMessage(@PathVariable long incidenceId, @PathVariable long messageId, Model model) {
        FullUserDTO user = (FullUserDTO) model.getAttribute("userlogin");

        messageService.delete(messageId, incidenceId, user.getId());
        return "redirect:/incidencia/" + incidenceId;
    }

    @GetMapping("/editar/mensaje/{incidenceId}/{messageId}")
    public String updateMessage(@PathVariable long incidenceId, @PathVariable long messageId, Model model) {

        FullUserDTO user = (FullUserDTO) model.getAttribute("userlogin");

        FullUserDTO owner = null;

        if (userIncidenceService.findByIncidenceId(incidenceId).getUser() != null) {
            owner = userService.getUserBy(userIncidenceService.findByIncidenceId(incidenceId).getUser().getId());
        } else owner = null;

        IncidenceMessageDTO messageToUpdate = messageService.findByIdAndIncidenceIdAndUserId(messageId, incidenceId, user.getId());
        if (messageToUpdate == null) {
            throw new RuntimeException("No se ha encontrado el mensaje");
        }

        FullIncidenceDTO incidenceDTO = incidenceService.findById(incidenceId);

        incidenceDTO.setIncidenceFiles(incidenceFileService.findAllByIncidenceId(incidenceId));
        List<IncidenceMessageDTO> incidenceMessageDTOS = messageService.findAllByIncidenceId(incidenceId);
        incidenceDTO.setMessages(incidenceMessageDTOS);

        model.addAttribute("message", messageService.findById(messageId));
        model.addAttribute("isUpdateMessage", true);
        model.addAttribute("incidence", incidenceDTO);
        model.addAttribute("userOwner", owner);
        return "view/incidence";
    }

    @PostMapping("/editar/mensaje/{incidenceId}/{messageId}")
    public String updateMessage(@PathVariable long incidenceId, @PathVariable long messageId, @Valid IncidenceMessageDTO incidenceMessageDTO, BindingResult result, Model model) {
        FullUserDTO user = (FullUserDTO) model.getAttribute("userlogin");

        System.out.println(incidenceMessageDTO);

        if (result.hasErrors()) {
            return "redirect:/editar/mensaje/" + incidenceId + "/" + messageId;
        }

        incidenceMessageDTO.setId(messageId);
        messageService.update(incidenceMessageDTO, incidenceId, messageId, user.getId());
        return "redirect:/incidencia/" + incidenceId;
    }
}
