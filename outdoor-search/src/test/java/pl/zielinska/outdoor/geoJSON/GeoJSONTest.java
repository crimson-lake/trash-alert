package pl.zielinska.outdoor.geoJSON;

import org.junit.Test;
import pl.zielinska.outdoor.TestVal;
import pl.zielinska.outdoor.domain.Ad;
import pl.zielinska.outdoor.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GeoJSONTest {

    private User testUser = User.builder()
            .username(TestVal.TEST_USERNAME)
            .firstName(TestVal.TEST_FIRST_NAME)
            .lastName(TestVal.TEST_LAST_NAME)
            .email(TestVal.TEST_EMAIL)
            .password(TestVal.TEST_PASSWORD)
            .authority("USER")
            .enabled(true)
            .build();

    private Ad testAd = Ad.builder()
            .id(TestVal.TEST_ID)
            .title(TestVal.TEST_TITLE)
            .city(TestVal.TEST_CITY)
            .street(TestVal.TEST_STREET)
            .created(TestVal.TEST_TIME)
            .coordinates(TestVal.TEST_COORDINATES)
            .adAuthor(testUser)
            .build();

    @Test
    public void createGeoJSONFromAdTest() {
        GeoJSON geoJSON = new GeoJSON(testAd);

        assertNotNull(geoJSON);
        assertNotNull(geoJSON.getGeometry());
    }

    @Test
    public void geoJSONTypeFeatureTest() {
        GeoJSON geoJSON = new GeoJSON(testAd);
        assertEquals("Feature", geoJSON.getType());
    }

    @Test
    public void geometryTypePointTest() {
        GeoJSON geoJSON = new GeoJSON(testAd);
        assertEquals("Point", geoJSON.getGeometry().getType());
    }
}
