package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.service.TokenService;
import com.quicksolve.proyecto.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@RestController
@Slf4j
@SessionAttributes({"userlogin"})
public class AuthRestController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;


}
