package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.service.IncidenceFileService;
import com.quicksolve.proyecto.service.IncidenceService;
import com.quicksolve.proyecto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"userlogin"})
public class IncidenceFileController {


    @Autowired
    private IncidenceFileService incidenceFileService;

    @Autowired
    private IncidenceService incidenceService;

    @Autowired
    private UserService userService;

    @GetMapping("/img/{idIncidence}/{idFile}")
    public String showImage(@PathVariable long idIncidence, @PathVariable long idFile, Model model) {

        FullUserDTO user = (FullUserDTO) model.getAttribute("userlogin");

        if (user == null)
            throw new IllegalStateException("No user logged in");

        incidenceFileService.deleteByIdAndIncidenceId(idFile, idIncidence);
        return "redirect:" + "/incidencia/modificar/" + idIncidence;
    }


}
