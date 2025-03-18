package com.nguyenkenny.anilog.validation;

import com.nguyenkenny.anilog.dto.UserRegistrationDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        UserRegistrationDto userRegistrationDto = (UserRegistrationDto) object;
        return userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword());
    }
}
