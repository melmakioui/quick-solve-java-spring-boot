package com.quicksolve.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlansController {

    @GetMapping("/planes")
    public String renderPlans(){
        return "view/planes";
    }
}
