package com.quicksolve.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"userlogin"})
public class HomeController {

    @GetMapping("/")
    public String renderIndex(){
        return "index";
    }
}
