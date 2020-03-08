package pl.zielinska.trashAlert.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = AdressValidator.class)
@Documented
public @interface Adress {

    String message() default "Invalid adress";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
