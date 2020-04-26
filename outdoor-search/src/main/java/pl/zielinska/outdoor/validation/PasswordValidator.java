package pl.zielinska.outdoor.validation;

import pl.zielinska.outdoor.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ConfirmedPassword, UserDto> {
    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        if (userDto.getPassword().equals(userDto.getConfirmPassword())) {
            return true;
        }
        return false;
    }
}
