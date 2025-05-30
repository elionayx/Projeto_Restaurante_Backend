package com.example.restaurante.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class CheckEmailValidator implements ConstraintValidator<ApenasCheckEmail, String> {

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        if (Objects.nonNull(valor) && valor.contains("@check.com.br")){
            return true;
        }
        return false;
    }
}
