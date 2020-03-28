package pl.zielinska.trashAlert.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zielinska.trashAlert.dao.AdRepository;
import pl.zielinska.trashAlert.domain.geoJSON.GeoJSON;
import pl.zielinska.trashAlert.domain.geoJSON.GeoJSONCollection;
import pl.zielinska.trashAlert.service.AdService;
import pl.zielinska.trashAlert.service.GeoService;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class GeoJSONTest {
    private final static Logger LOGGER = Logger.getLogger("GeoJsonTest");

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private AdService adService;

    @Autowired
    private GeoService geoService;

    @Test
    void createGeoJSONFromAd() throws JsonProcessingException {
        Ad ad = adRepository.findById(9).get();
        assertNotNull(ad);
        GeoJSON geoJSON = new GeoJSON(ad);

        assertNotNull(geoJSON);
        assertNotNull(geoJSON.getGeometry());
    }

    @Test
    void createGeoJSONDataFromAllAdds() throws JsonProcessingException {
        GeoJSONCollection allCoordinates = geoService.getCoordinates();
        assertNotNull(allCoordinates);
        LOGGER.info(allCoordinates.toString());
    }
}
