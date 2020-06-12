package pl.zielinska.outdoor.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import pl.zielinska.model.util.CoordinatesUtil;
import pl.zielinska.outdoor.dto.Address;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AddressValidator implements ConstraintValidator<pl.zielinska.outdoor.validation.Address, Address> {

    @Override
    public void initialize(pl.zielinska.outdoor.validation.Address constraintAnnotation) {}

    @Override
    public boolean isValid(Address address, ConstraintValidatorContext constraintValidatorContext) {
        ResponseEntity<String> response = CoordinatesUtil.getResponseFor(address.getCity(), address.getStreet());
        if (response.hasBody()) {
            try {
                JsonNode features = new ObjectMapper()
                        .readTree(response.getBody())
                        .get("features");
                if (features.isEmpty()) {
                    return false;
                }
            } catch (JsonProcessingException e) {
                return false;
            }
        }
        return true;
    }
}
