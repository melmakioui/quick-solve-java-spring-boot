package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"userlogin"})
public class CuentaController {

    @Autowired
    private UserService userService;

    @GetMapping("/cuenta")
    public String showAccount(){
        return "view/account";
    }

    @PostMapping("/cuenta")
    @ResponseBody
    public String updateAccount(
            @RequestParam("username") String username,
            @RequestParam("name") String name,
            @RequestParam("firstSurname") String firstSurname,
            @RequestParam(value = "secondSurname", required = false) String secondSurname,
            Model model
    ){
        System.out.println(username);
        System.out.println(name);
        System.out.println(firstSurname);
        System.out.println(secondSurname);
        return "view/account";
    }
}
