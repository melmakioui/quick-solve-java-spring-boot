package com.quicksolve.proyecto.configuration;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component("Interceptor")
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        if (!(session.getAttribute("userlogin") instanceof FullUserDTO)){
            response.sendRedirect("/login");
            return false;
        }

        String auth = request.getHeader("Authorization");
        if (auth != null && !auth.isEmpty()){
            String token = auth.replace("Bearer ", "");

            int validate = tokenService.validateToken(token);
            if (validate == 1 || validate == 2) {
                String errorMessage = validate == 1 ? "No valid token" : "Expired token";
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, errorMessage);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }

            FullUserDTO usr = (FullUserDTO) session.getAttribute("userlogin");
            System.out.println(usr);
            String URI = request.getRequestURI();
            String[] rolsURL = getRolsFromURL(URI);

            return this.hasAuth(usr, rolsURL);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is missing");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }

    private String[] getRolsFromURL(String URI){
        Multimap<String, String[]> securedURLs = LinkedHashMultimap.create();
        String[] urls = {"/cuenta", "/incidencias", "/incidencia", "/imagenes", "/guardar", "/borrar", "/editar", "/modificar", "/logout"};
        String[] allRoles = {"USER", "TECH", "ADMIN"};

        // URLS FOR ALL REGISTERED USERS, TECHS AND ADMINS
        for (String url : urls) {
            securedURLs.put(url, allRoles);
        }
        // RESTRICTED URLS GO HERE
        securedURLs.put("/planes", new String[]{"USER", "ADMIN"});
        securedURLs.put("/invoices", new String[]{"USER", "ADMIN"});

        Map.Entry<String, String[]> securedURL = securedURLs.entries().stream()
                .filter(urlsecured -> URI.contains(urlsecured.getKey()))
                .findFirst().orElse(null);
        return securedURL != null ? securedURL.getValue() : null;
    }

    private boolean hasAuth(FullUserDTO usr, String... allowedRoles){
        if (allowedRoles == null) return true; // Se permite el acceso a todos (no es una URL RESTRINGIDA)
        if (usr == null) return false;

        for (String rol : allowedRoles){
            if (usr.getType().name().contains(rol)){
                return true;
            }
        }

        return false;
    }
}
