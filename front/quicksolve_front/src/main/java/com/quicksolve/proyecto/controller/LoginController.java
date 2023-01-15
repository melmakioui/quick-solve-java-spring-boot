package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.entity.User;
import com.quicksolve.proyecto.entity.UserData;
import com.quicksolve.proyecto.entity.type.UserType;
import com.quicksolve.proyecto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;


@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String renderIndex(){
        return "index";
    }

    @GetMapping("/login")
    public String renderLogin(){
        return "view/login";
    }

    @GetMapping("/registro")
    public String renderRegister(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "view/registro";
    }

    @PostMapping("/registro")
    public String registerUser(
            @ModelAttribute User user,
            @RequestParam("name") String name,
            @RequestParam("firstSurname") String firstSurname,
            @RequestParam("secondSurname") String secondSurname
    ){
        user.setActive(true);
        user.setType(UserType.USER);
        UserData data = new UserData(
                name,
                firstSurname,
                secondSurname,
                LocalDateTime.now(),
                user
        );

        userService.createUser(user, data);
        return "index"; //TODO devolver a "mis incidencias con el usuario ya iniciado sesión, para mejor UX."
    }
}
