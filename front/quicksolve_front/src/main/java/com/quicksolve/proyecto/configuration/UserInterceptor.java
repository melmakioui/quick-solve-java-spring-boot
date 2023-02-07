package com.quicksolve.proyecto.configuration;

import com.quicksolve.proyecto.dto.FullUserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component("Interceptor")
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        if (!(session.getAttribute("userlogin") instanceof FullUserDTO)){
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
