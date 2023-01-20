package com.quicksolve.proyecto.validator;

import com.quicksolve.proyecto.service.UserService;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmail.UniqueEmailValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "This email already exists.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

        @Autowired
        private UserService userService;

        @Override
        public void initialize(UniqueEmail constraintAnnotation) {}

        @Override
        public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
            return !userService.existsWithEmail(email);
        }
    }
}
