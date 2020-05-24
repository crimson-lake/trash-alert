package pl.zielinska.outdoor.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.zielinska.outdoor.TestVal;
import pl.zielinska.model.domain.Coordinates;
import pl.zielinska.model.util.CoordinatesUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CoordinatesUtilTest {

    @Test
    public void getResponceOKTest() {
        ResponseEntity<String> response = CoordinatesUtil.getResponseFor(TestVal.TEST_CITY, TestVal.TEST_STREET);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void translateAddressToCoordinatesTest() throws JsonProcessingException {
        Coordinates xy = CoordinatesUtil.translateAdressToCoordinates(TestVal.TEST_CITY, TestVal.TEST_STREET);
        assertNotNull(xy);
        assertEquals(TestVal.TEST_COORDINATES_X, xy.getX());
        assertEquals(TestVal.TEST_COORDINATES_Y, xy.getY());
    }
}
