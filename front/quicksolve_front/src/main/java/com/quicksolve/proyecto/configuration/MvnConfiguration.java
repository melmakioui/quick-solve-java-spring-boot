package com.quicksolve.proyecto.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MvnConfiguration implements WebMvcConfigurer {
    @Autowired
    @Qualifier("Interceptor")
    private HandlerInterceptor UserInterceptor;

    @Autowired
    @Qualifier("LanguageInterceptor")
    private HandlerInterceptor LanguageInterceptor;

    @Value("${upload}")
    private String upload;


    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(LanguageInterceptor)
                .addPathPatterns("/**");
        registry.addInterceptor(UserInterceptor)
                .addPathPatterns("/incidencias/**", "/incidencia/**", "/nueva/**", "/modificar/**", "/planes/**", "/cuenta/**","/editar/**","guardar/**","/borrar/**", "/invoices/**","/historial/**", "/rest/**", "/chats/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(upload);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/errorno.html").setViewName("errorno");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
