package pl.zielinska.outdoor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zielinska.outdoor.dao.AdRepository;
import pl.zielinska.outdoor.domain.Ad;
import pl.zielinska.outdoor.domain.geoJSON.GeoJSONCollection;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GeoServiceImpl implements GeoService, InitializingBean {

    @Autowired
    private AdRepository adRepository;

    private GeoJSONCollection coordinates;

    @Override
    public void afterPropertiesSet() throws Exception {
        coordinates = getAllCoordinates();
    }

    @Override
    public GeoJSONCollection getCoordinates() {
        return coordinates;
    }

    @Override
    public void adNewCoordinates(Ad theAd) {
        try {
            coordinates.addFeature(theAd);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private GeoJSONCollection getAllCoordinates() throws JsonProcessingException {
        List<Ad> allAds = adRepository.findAll();
        GeoJSONCollection coordinates = new GeoJSONCollection();
        for (Ad ad : allAds) {
            coordinates.addFeature(ad);
        }
        return coordinates;
    }
}
