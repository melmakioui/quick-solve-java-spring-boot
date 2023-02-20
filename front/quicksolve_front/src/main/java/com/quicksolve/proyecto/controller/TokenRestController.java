package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.service.TokenService;
import com.quicksolve.proyecto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenRestController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/token/crear")
    public @ResponseBody String crearToken(@RequestBody String jsonData) throws JSONException {
        JSONObject json = new JSONObject(jsonData);
        String email = json.getString("email");

        return tokenService.createToken(email, userService.getUserBy(email).getType().name());
    }
}
