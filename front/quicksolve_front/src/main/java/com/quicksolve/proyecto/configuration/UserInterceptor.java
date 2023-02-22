package com.quicksolve.proyecto.configuration;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.entity.type.UserType;
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
    private final String[] REST_URLs = {"/rest/imagenes", "/rest/incidencias/tech", "/rest/incidencias/buscar"};


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        if (!(session.getAttribute("userlogin") instanceof FullUserDTO)){
            response.sendRedirect("/login");
            return false;
        }

        String auth = request.getHeader("Authorization");
        if (uriContainsRestURL(request.getRequestURI())){
            if ((auth != null && !auth.isEmpty())){
                String token = auth.replace("Bearer ", "");

                int validate = tokenService.validateToken(token);
                if (validate == 1 || validate == 2) {
                    String errorMessage = validate == 1 ? "No valid token" : "Expired token";
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, errorMessage);
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return false;
                }

                FullUserDTO usr = (FullUserDTO) session.getAttribute("userlogin");
                String URI = request.getRequestURI();
                String[] rolsURL = getRolsFromURL(URI);

                return this.hasAuth(usr, rolsURL);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is missing");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }
        return true;
    }

    private boolean uriContainsRestURL(String uri){
        for ( String url : REST_URLs ) {
            if (uri.contains(url)) return true;
        }
        return false;
    }

    // RESTRINGIR URLS REST -->
    private String[] getRolsFromURL(String URI){
        Multimap<String, String[]> securedURLs = LinkedHashMultimap.create();
        String[] allRoles = {UserType.USER.name(), UserType.TECH.name(), UserType.ADMIN.name()};

        // URLS FOR ALL REGISTERED USERS, TECHS AND ADMINS
        securedURLs.put(REST_URLs[0], allRoles);
        // RESTRICTED URLS GO HERE
        securedURLs.put(REST_URLs[1], new String[]{UserType.TECH.name(), UserType.ADMIN.name()});
        securedURLs.put(REST_URLs[2], new String[]{UserType.TECH.name(), UserType.ADMIN.name()});

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
