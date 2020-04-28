package pl.zielinska.outdoor.domain;

import org.junit.Test;
import pl.zielinska.outdoor.TestVal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdTest {

    @Test
    public void coordinatesNullTest() {
        Ad ad = Ad.builder()
                .title(TestVal.TEST_TITLE)
                .city(TestVal.TEST_CITY)
                .street(TestVal.TEST_STREET)
                .created(TestVal.TEST_TIME)
                .coordinates(null)
                .build();
        assertNotNull(ad.getCoordinates());

        Coordinates xy = ad.getCoordinates();
        assertEquals(TestVal.TEST_COORDINATES_Y, xy.getY());
        assertEquals(TestVal.TEST_COORDINATES_X, xy.getX());
    }
}
