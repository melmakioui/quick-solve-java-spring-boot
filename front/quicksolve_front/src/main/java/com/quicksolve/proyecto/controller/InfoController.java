package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("{userlogin}")
public class InfoController {

    @Autowired
    private EmailService emailService;

    @Value("${mailgun.from}")
    private String from;

    @GetMapping("/politica-privacidad")
    public String privacy(){
        return "view/policy";
    }

    @GetMapping("/contacto")
    public String contact(){
        return "view/contact";
    }

    @GetMapping("/sobre-nosotros")
    public String aboutUs(){
        return "view/sobre-nosotros";
    }

    @PostMapping("/contacto")
    public String contactPost(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("message") String message, Model model){

        String html = "<html><body><p>Nombre: " + name + "</p><p>Email: " + email + "</p><p>Mensaje: " + message + "</p></body></html>";
        emailService.sendGenericEmail(from, html);
        return "view/contactNotifier";
    }

}


