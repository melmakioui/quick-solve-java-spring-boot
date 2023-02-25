package com.quicksolve.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"userlogin"})
public class InfoController {

    @GetMapping("/politica-privacidad")
    public String privacy() {
        return "view/policy";
    }

    @GetMapping("/contacto")
    public String contact() {
        return "view/contact";
    }

    @PostMapping("/contacto")
    public String contactPost(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("message") String message, Model model) {
        return "view/contact";
    }

}
