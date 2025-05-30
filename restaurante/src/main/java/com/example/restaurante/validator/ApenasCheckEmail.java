package com.example.restaurante.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CheckEmailValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApenasCheckEmail {

    String message() default " E-mail invalido, não contém '@check.com.br' ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
