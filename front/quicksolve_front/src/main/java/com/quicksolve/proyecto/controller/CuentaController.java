package com.quicksolve.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"userlogin"})
public class CuentaController {

    @GetMapping("/cuenta")
    public String showAccount(Model model){
        System.out.println(model.getAttribute("userlogin"));
        return "view/account";
    }

    @PostMapping("/cuenta")
    public String updateAccount(){
        return "view/account";
    }
}
