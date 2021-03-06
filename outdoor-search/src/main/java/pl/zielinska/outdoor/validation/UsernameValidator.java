package pl.zielinska.outdoor.validation;

import org.springframework.beans.factory.annotation.Autowired;
import pl.zielinska.outdoor.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userService.usernameAvailable(s);
    }
}
