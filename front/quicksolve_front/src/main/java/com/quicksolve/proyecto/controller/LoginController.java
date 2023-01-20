package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"user"})
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String renderLogin(Model model){
        model.addAttribute("user",null);
        return "view/login";
    }

    @PostMapping("/login")
    public String tryLogin(){
        
        return "index";
    }

}
