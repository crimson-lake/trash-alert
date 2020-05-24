package pl.zielinska.outdoor.geoJSON;

import org.junit.Test;
import pl.zielinska.outdoor.TestVal;
import pl.zielinska.model.domain.Ad;
import pl.zielinska.model.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GeoJSONCollectionTest {

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
            .adAuthor(testUser)
            .coordinates(TestVal.TEST_COORDINATES)
            .build();

    @Test
    public void addFeatureTest() {
        GeoJSONCollection collection = new GeoJSONCollection();
        collection.addFeature(testAd);
        assertNotNull(collection);
        assertEquals(1, collection.getFeatures().size());
    }

    @Test
    public void typeFeatureCollectionTest() {
        GeoJSONCollection collection = new GeoJSONCollection();
        assertEquals("FeatureCollection", collection.getType());
    }

}
