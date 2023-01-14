package com.quicksolve.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping
    public String renderIndex(){
        return "index";
    }

    @GetMapping("/login")
    public String renderLogin(){
        return "view/login";
    }

    @GetMapping("/registro")
    public String renderRegistro(){
        return "view/registro";
    }
}
