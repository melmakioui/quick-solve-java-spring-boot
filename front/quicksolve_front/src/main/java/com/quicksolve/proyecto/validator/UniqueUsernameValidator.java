package com.quicksolve.proyecto.validator;

import com.quicksolve.proyecto.service.UserService;
import com.quicksolve.proyecto.utility.AppContextHolder;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserService userService = AppContextHolder.getBean(UserService.class);

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {}

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.existsWithUsername(username);
    }
}
