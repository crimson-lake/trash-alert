package pl.zielinska.trashAlert.validation;

import pl.zielinska.trashAlert.domain.Ad;
import pl.zielinska.trashAlert.domain.geoJSON.GeoJSON;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AddressValidator implements ConstraintValidator<Address, Ad> {

    @Override
    public void initialize(Address constraintAnnotation) {}

    @Override
    public boolean isValid(Ad ad, ConstraintValidatorContext constraintValidatorContext) {
        try {
            new GeoJSON(ad);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
