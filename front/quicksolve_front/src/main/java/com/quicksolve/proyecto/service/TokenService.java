package com.quicksolve.proyecto.service;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

public interface TokenService {
    String createToken(String email, String rol);
    String createTokenForValidation(String email, String rol);
    int validateToken(String token);
    Claims getClaims(HttpServletRequest request);
    Claims getClaims(String token);
}
