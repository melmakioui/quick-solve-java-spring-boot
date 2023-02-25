package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.service.EmailService;
import com.quicksolve.proyecto.service.TokenService;
import com.quicksolve.proyecto.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VerifyAccountController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Value("${url}")
    private String URL;

    @GetMapping("/verificar")
    public String verifyAccount(@RequestParam("code") String token, Model model){

        int verify = tokenService.validateToken(token);

        if(verify == 0) {
            model.addAttribute("loginError", "false");
            model.addAttribute("isVerified", "true");

            Claims claims = tokenService.getClaims(token);
            String email = claims.get("email", String.class);
            userService.activateUser(email);
            model.addAttribute("isAccountVerified", true);
            return "view/success";
        }

        return "errorno";
    }

    @GetMapping("/recuperar-cuenta")
    public String renderRecoverAccount(Model model){
        return "view/accountRecovery";
    }

    @PostMapping("/recuperar-cuenta")
    public String recoverAccount(@RequestParam("email") String email, Model model){

        FullUserDTO user = userService.getUserBy(email);

        if (user == null) {
            model.addAttribute("found", "false");
            return "view/accountRecovery";
        }

        String token = tokenService.createToken(user.getEmail(),user.getType().name());
        String html = "<html><body><p>Para verificar tu cuenta haz click en el siguiente enlace : <a href='"+ URL +"/cambiar-contrasena?code=" + token + "'>Verificar</a></p></body></html>";

        emailService.sendEmailVerificationAccount(user.getEmail(), html);

        return "view/notifier";
    }

    @GetMapping("/cambiar-contrasena")
    public String renderChangePassword(@RequestParam("code") String token, Model model){

        if (token == null) return "errorno";

        int verify = tokenService.validateToken(token);

        if(verify == 0) {
            Claims claims = tokenService.getClaims(token);
            String email = claims.get("email", String.class);
            return "view/changePassword";
        }

        return "errorno";
    }

    @PostMapping("/cambiar-contrasena")
    public String changePassword(@RequestParam("code") String token, @RequestParam("password") String password, @RequestParam("confirmpassword") String confirmpassword, Model model){

        int verify = tokenService.validateToken(token);

        if (!password.equals(confirmpassword)) {
            model.addAttribute("notEqual", true);
            return "view/changePassword";
        }

        if(verify == 0) {
            model.addAttribute("loginError", "false");
            model.addAttribute("isVerified", "true");

            Claims claims = tokenService.getClaims(token);
            String email = claims.get("email", String.class);
            userService.changePassword(email, password);
            model.addAttribute("isPasswordChanged", true);
            return "view/success";
        }
        return "errorno";
    }

}
