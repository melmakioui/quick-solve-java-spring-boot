package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.service.TokenService;
import com.quicksolve.proyecto.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VerifyAccountController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @GetMapping("/verify")
    public String verifyAccount(@RequestParam("code") String token, Model model){

        int verify = tokenService.validateToken(token);

        if(verify == 0) {
            model.addAttribute("loginError", "false");
            model.addAttribute("isVerified", "true");

            Claims claims = tokenService.getClaims(token);
            String email = claims.get("email", String.class);
            userService.activateUser(email);
            return "view/success";
        }

        return "errorno";
    }


}
