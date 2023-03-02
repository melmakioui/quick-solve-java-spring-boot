package com.quicksolve.proyecto.configuration;

import com.quicksolve.proyecto.dto.LanguageDTO;
import com.quicksolve.proyecto.service.LanguageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.List;
import java.util.Locale;

@Component("LanguageInterceptor")
public class LanguageInterceptor implements HandlerInterceptor {


    @Autowired
    private LanguageService languageService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        LocaleResolver localeResolver = new CookieLocaleResolver();
        Locale userLocale = localeResolver.resolveLocale(request);
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();

        lci.setParamName("lang");
        lci.preHandle(request, response, handler);

            List<LanguageDTO> languageDTO = languageService.getLanguage(userLocale.getLanguage());
            LanguageDTO languageDTO1 = new LanguageDTO();

            for (LanguageDTO lang : languageDTO) {
                languageDTO1.getKeyValue().put(lang.getKey(), lang.getValue());
            }

                HttpSession session = request.getSession();
                session.setAttribute("language", languageDTO1);
        return true;
    }
}
