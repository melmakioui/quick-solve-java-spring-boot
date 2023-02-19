package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.configuration.PasswordEncoderConf;
import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.service.TokenService;
import com.quicksolve.proyecto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthRestController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoderConf passwordEncoder;

    @PostMapping(value = "/loginTokenGenerate")
    public @ResponseBody ResponseEntity<?> validacioLogin(@RequestBody String jsonData) throws JSONException {
        JSONObject json = new JSONObject(jsonData);
        String email = json.getString("email");
        String password = json.getString("password");

        if(userService.existsWithEmail(email)) {
            FullUserDTO userDTO = userService.getUserBy(email);
            if (passwordEncoder.encoder().matches(password, userDTO.getPassword())) {

                String rol = "";
                if (userDTO.getType() != null) {
                    rol = userDTO.getType().name();
                }

                return new ResponseEntity<>(tokenService.createToken(email, rol), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not authorized", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("User not authorized", HttpStatus.UNAUTHORIZED);
        }
    }

}
