package pl.zielinska.outdoor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zielinska.outdoor.dao.AdRepository;
import pl.zielinska.outdoor.domain.Ad;
import pl.zielinska.outdoor.geoJSON.GeoJSONCollection;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GeoServiceImpl implements GeoService {

    @Autowired
    private AdRepository adRepository;

    @Override
    public GeoJSONCollection getCoordinates() {
        List<Ad> allAds = adRepository.findAll();
        GeoJSONCollection coordinates = new GeoJSONCollection();
        for (Ad ad : allAds) {
            coordinates.addFeature(ad);
        }
        return coordinates;
    }
}
