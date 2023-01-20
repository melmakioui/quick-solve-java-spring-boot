package com.quicksolve.proyecto.validator;

import com.quicksolve.proyecto.repository.UserRepository;
import com.quicksolve.proyecto.service.UserService;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUsername.UniqueUsernameValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {
    String message() default "This username already exists.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
        @Autowired
        private UserService userService;

        @Override
        public void initialize(UniqueUsername constraintAnnotation) {}

        @Override
        public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
            return !userService.existsWithUsername(username);
        }
    }
}
