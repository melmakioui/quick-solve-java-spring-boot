package com.quicksolve.proyecto.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@RestController
public class OAuthController {

    @PostMapping("/auth/google")
    public ResponseEntity<String> loginGoogle(@RequestBody String token) throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        GoogleIdTokenVerifier tokenVerifier = new GoogleIdTokenVerifier.Builder(
                HTTP_TRANSPORT,
                GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList("327064841381-lloi1on6ses7vmjr72h2lptajd3g14fn.apps.googleusercontent.com"))
                .build();
        GoogleIdToken tokenId = tokenVerifier.verify(token);
        GoogleIdToken.Payload payload = tokenId.getPayload();

        System.out.println("Email: " + payload.getEmail());
        System.out.println("Name: " + payload.get("given_name"));
        System.out.println("Surname: " + payload.get("family_name"));
        System.out.println("Username" + payload.get("name"));

        // TODO a partir del email crear mi token local
        String tokenLocal = "1234";

        return new ResponseEntity<>(tokenLocal, HttpStatus.OK);
    }

    /*@GetMapping("/horari")
    public ResponseEntity<List<String>> getHorari(HttpRequest request){
        // String token = request.getHeaders().get("Authorization");
        // Rol[] roles = token.getClaim("roles");
        return new ResponseEntity<>(, HttpStatus.OK);
    }*/
}
