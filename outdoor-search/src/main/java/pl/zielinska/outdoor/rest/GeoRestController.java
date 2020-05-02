package pl.zielinska.outdoor.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zielinska.outdoor.geoJSON.GeoJSONCollection;
import pl.zielinska.outdoor.service.GeoService;

@RestController
@RequestMapping("/api/ads")
@AllArgsConstructor
public class GeoRestController {

    @Autowired
    private GeoService geoService;

    @GetMapping(path = "/geoinfo")
    public GeoJSONCollection findGeoInfoForAllAds() throws JsonProcessingException {
        return geoService.getCoordinates();
    }
}
