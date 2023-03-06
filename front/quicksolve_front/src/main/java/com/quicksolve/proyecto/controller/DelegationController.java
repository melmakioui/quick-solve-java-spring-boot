package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.FullIncidenceDTO;
import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.entity.type.UserType;
import com.quicksolve.proyecto.service.*;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class DelegationController {

    @Autowired
    private DelegationService delegationService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Autowired
    private IncidenceService incidenceService;

    @Autowired
    private HistoryService historyService;

    @PutMapping( "/tech/assign")
    public ResponseEntity assignTechToIncidence(@RequestParam long incidenceId, @RequestParam long techId, @RequestHeader(value = "Authorization", required = true) String token) {

        String tokenWithoutBearer = token.substring(7);

        int tokenValidation = tokenService.validateToken(tokenWithoutBearer);

        if(tokenValidation != 0)
            throw new RuntimeException("Token no valido");

        Claims claims = tokenService.getClaims(tokenWithoutBearer);
        String rol = claims.get("rol", String.class);

        if (!rol.equals("DEP_HEAD"))
            throw new RuntimeException("No tienes permisos para realizar esta accion");

        delegationService.assignTechToIncidence(incidenceId, techId);

        FullUserDTO user = userService.getUserBy(techId);
        FullIncidenceDTO incidenceDTO = incidenceService.findById(incidenceId);

        String html = "<h1>Se te ha asignado una incidencia</h1>" +
                "<p>Se te ha asignado la incidencia con id: " + incidenceDTO.getId() + " y titulo: " + incidenceDTO.getTitle() + "</p>" +
                "<p>Para ver la incidencia, accede a la aplicacion</p>";

        emailService.sendGenericEmail(user.getEmail(), html);

        return ResponseEntity.ok().build();
    }
}
