package com.plf.boot.unified.customize.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class PasswordValidator implements ConstraintValidator<Password,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        log.info("PasswordValidator start to Validate");
        if (value != null && value.trim().length() != 0) {
            for (int i = 0; i < value.length(); i++) {
                if (!isNumber(value.charAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isNumber(char ch){
        return Character.isDigit(ch);
    }
}
