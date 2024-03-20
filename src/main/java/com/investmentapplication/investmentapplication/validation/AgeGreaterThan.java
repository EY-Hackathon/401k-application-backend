package com.investmentapplication.investmentapplication.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AgeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AgeGreaterThan {
    String message() default "Age must be greater than 18";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
