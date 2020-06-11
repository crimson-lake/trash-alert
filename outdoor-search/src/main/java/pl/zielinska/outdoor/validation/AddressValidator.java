package pl.zielinska.outdoor.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import pl.zielinska.model.util.CoordinatesUtil;
import pl.zielinska.outdoor.dto.Adress;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AddressValidator implements ConstraintValidator<Address, Adress> {

    @Override
    public void initialize(Address constraintAnnotation) {}

    @Override
    public boolean isValid(Adress adress, ConstraintValidatorContext constraintValidatorContext) {
        ResponseEntity<String> response = CoordinatesUtil.getResponseFor(adress.getCity(), adress.getStreet());
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
