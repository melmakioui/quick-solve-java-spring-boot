package com.quicksolve.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"userlogin"})
public class IncidenceTechController {

    @GetMapping("/incidencias/tech")
    public String showIncidencesTech(){
        return "view/incidencesTech";
    }
}
