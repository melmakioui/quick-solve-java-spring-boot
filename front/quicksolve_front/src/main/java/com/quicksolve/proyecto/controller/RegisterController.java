package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.dto.UserDataDTO;
import com.quicksolve.proyecto.entity.User;
import com.quicksolve.proyecto.service.EmailService;
import com.quicksolve.proyecto.service.TokenService;
import com.quicksolve.proyecto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@SessionAttributes({"userlogin"})
@Validated
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailService emailService;

    @Value("${url}")
    private String URL;

    @GetMapping("/registro")
    public String renderRegister(Model model){
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("alreadyExistsEmail", "false");
        model.addAttribute("alreadyExistsUsername", "false");
        return "view/registro";
    }

    @PostMapping("/registro")
    public String registerUser(
            FullUserDTO user,
            @RequestParam("name") String name,
            @RequestParam("firstSurname") String firstSurname,
            @RequestParam(value = "secondSurname", required = false) String secondSurname,
            Model model
    ){

        model.addAttribute("user", user);
        if (userService.existsWithEmail(user.getEmail())){
            model.addAttribute("alreadyExistsEmail", "true");
        }

        if (userService.existsWithUsername(user.getUsername())){
            model.addAttribute("alreadyExistsUsername", "true");
        }

        if (userService.existsWithEmail(user.getEmail()) || userService.existsWithUsername(user.getUsername())){
            return "view/registro";
        }

        user.setData(new UserDataDTO(name, firstSurname, secondSurname, LocalDateTime.now()));

        //Verificacion de email
        FullUserDTO totalUser = userService.createUser(user);
        String tokenUser = tokenService.createTokenForValidation(totalUser.getEmail(),totalUser.getType().name());
        String html = "<html><body><h1>Verifica tu cuenta</h1><p>Para verificar tu cuenta, haz click en el siguiente enlace: <a href='" + URL +"/verificar?code=" + tokenUser + "'>Verificar</a></p></body></html>";
        emailService.sendGenericEmail(totalUser.getEmail(), html);
        return "view/notifier";
    }
}
