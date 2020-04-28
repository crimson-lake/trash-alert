package pl.zielinska.outdoor.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.zielinska.outdoor.domain.Coordinates;
import pl.zielinska.outdoor.util.CoordinatesUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CoordinatesUtilTest {

    @Test
    public void getResponceOKTest() {
        ResponseEntity<String> response = CoordinatesUtil.getResponseFor("Kraków", "Włóczków 10");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void translateAddressToCoordinatesTest() throws JsonProcessingException {
        Coordinates xy = CoordinatesUtil.translateAdressToCoordinates("Kraków", "Włóczków 10");
        assertNotNull(xy);
        assertEquals(19.924355, xy.getX());
        assertEquals(50.0554964, xy.getY());
    }
}
