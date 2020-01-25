package pl.zielinska.trashAlert;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zielinska.trashAlert.dao.AdRepository;
import pl.zielinska.trashAlert.entity.Ad;
import pl.zielinska.trashAlert.entity.geoJSON.GeoJSON;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class GeoJSONTest {

    @Autowired
    private AdRepository adRepository;

    @Test
    void createGeoJSONFromAd() throws JsonProcessingException {
        Ad ad = adRepository.findById(9).get();
        assertNotNull(ad);
        GeoJSON geoJSON = new GeoJSON(ad);

        assertNotNull(geoJSON);
        assertNotNull(geoJSON.getGeometry());
    }
}
