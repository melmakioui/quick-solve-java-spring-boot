package com.quicksolve.proyecto.utility;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AppContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }
    @Override
    public void setApplicationContext(@SuppressWarnings("NullableProblems") ApplicationContext applicationContext) throws BeansException {
        AppContextHolder.applicationContext = applicationContext;
    }
}
