package pl.zielinska.trashAlert.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import pl.zielinska.trashAlert.TestVal;
import pl.zielinska.trashAlert.domain.geoJSON.GeoJSON;

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
            .adAuthor(testUser)
            .build();

    @Test
    public void createGeoJSONFromAd() throws JsonProcessingException {
        GeoJSON geoJSON = new GeoJSON(testAd);

        assertNotNull(geoJSON);
        assertNotNull(geoJSON.getGeometry());
    }
}
