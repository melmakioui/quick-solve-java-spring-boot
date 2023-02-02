package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.service.implementation.ServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"userlogin"})
public class PlansController {

    @Autowired
    ServiceServiceImpl serviceServ;

    @GetMapping("/planes")
    public String renderPlans(Model model){
        model.addAttribute("planes", serviceServ.showAll());
        return "view/planes";
    }
}
