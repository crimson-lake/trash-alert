package pl.zielinska.trashAlert.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import pl.zielinska.trashAlert.domain.geoJSON.GeoJSON;
import pl.zielinska.trashAlert.dto.AdDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AddressValidator implements ConstraintValidator<Address, AdDto> {

    @Override
    public void initialize(Address constraintAnnotation) {}

    @Override
    public boolean isValid(AdDto ad, ConstraintValidatorContext constraintValidatorContext) {
        ResponseEntity<String> response = GeoJSON.getResponseFor(ad.getCity(), ad.getStreet());
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
