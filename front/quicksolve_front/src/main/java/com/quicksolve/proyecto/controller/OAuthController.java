package com.quicksolve.proyecto.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.dto.UserDataDTO;
import com.quicksolve.proyecto.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@SessionAttributes({"userlogin"})
public class OAuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/google")
    public ResponseEntity<String> loginGoogle(@RequestBody String token, Model model) throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        GoogleIdTokenVerifier tokenVerifier = new GoogleIdTokenVerifier.Builder(
                HTTP_TRANSPORT,
                GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList("327064841381-lloi1on6ses7vmjr72h2lptajd3g14fn.apps.googleusercontent.com")) //Variable de entorno
                .build();
        GoogleIdToken tokenId = tokenVerifier.verify(token);
        GoogleIdToken.Payload payload = tokenId.getPayload();

        FullUserDTO user = userService.getUserBy(payload.getEmail());

        if (user == null) {
            user = new FullUserDTO();
            user.setEmail(payload.getEmail());
            user.setUsername(payload.get("name").toString());
            UserDataDTO userData = new UserDataDTO();
            userData.setName(payload.get("given_name").toString());
            userData.setFirstSurname(payload.get("family_name").toString());
            userData.setCreated(LocalDateTime.now());
            user.setData(userData);
            user.setOauth(true);
            user.setActive(true);
            userService.createUser(user);
        }

        FullUserDTO userlogin = userService.getUserBy(payload.getEmail());
        FullUserDTO fullUserDTO = userService.getFullUser(userlogin.getId());
        model.addAttribute("userlogin", fullUserDTO );

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*@GetMapping("/horari")
    public ResponseEntity<List<String>> getHorari(HttpRequest request){
        // String token = request.getHeaders().get("Authorization");
        // Rol[] roles = token.getClaim("roles");
        return new ResponseEntity<>(, HttpStatus.OK);
    }*/
}
