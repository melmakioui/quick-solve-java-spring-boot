package com.quicksolve.proyecto.configuration;

import com.quicksolve.proyecto.dto.LanguageDTO;
import com.quicksolve.proyecto.service.LanguageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;
import java.util.Locale;

@Component("LanguageInterceptor")
public class LanguageInterceptor implements HandlerInterceptor {


    @Autowired
    private LanguageService languageService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //TODO segun el parametro traer el idioma del servicio

        String language = request.getParameter("lang");
        String finalLanguage = "";

        if (language == null) {
            finalLanguage = request.getHeader("Accept-Language");
            if (finalLanguage == null) {
                finalLanguage = Locale.getDefault().getLanguage();
            }else {
                finalLanguage = finalLanguage.substring(0, 2);
            }
        }else {
            finalLanguage = language.substring(0, 2);
        }

            List<LanguageDTO> languageDTO = languageService.getLanguage(finalLanguage); //TODO segun el parametro traer el idioma del servicio
            LanguageDTO languageDTO1 = new LanguageDTO();

            for (LanguageDTO lang : languageDTO) {
                languageDTO1.getKeyValue().put(lang.getKey(), lang.getValue());
            }

                HttpSession session = request.getSession();
                session.setAttribute("language", languageDTO1);
        return true;
    }

}
