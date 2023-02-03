package com.quicksolve.proyecto.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvnConfiguration implements WebMvcConfigurer {
    @Autowired
    @Qualifier("Interceptor")
    private HandlerInterceptor UserInterceptor;

    @Autowired
    private Environment env;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(UserInterceptor)
                .addPathPatterns("/incidencias/**", "/incidencia/**", "/nueva/**", "/modificar/**", "/planes/**", "/cuenta");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(env.getProperty("upload"));
    }

    // CORS para pruebas
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedOrigins("http://localhost:5500");
    }
}
