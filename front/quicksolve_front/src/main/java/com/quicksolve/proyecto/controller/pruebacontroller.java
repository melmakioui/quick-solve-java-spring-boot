package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class pruebacontroller {

    @Autowired
    private UserService userService;
    @GetMapping("/yolo")
    public FullUserDTO xd(){
        return userService.getFullUser(1L);
    }

    @GetMapping("/lenguaje")
    public Locale xddd(){
        Locale locale = LocaleContextHolder.getLocale();
        return locale;
    }
}
