package pl.zielinska.trashAlert.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zielinska.trashAlert.dao.AdRepository;
import pl.zielinska.trashAlert.entity.Ad;
import pl.zielinska.trashAlert.entity.geoJSON.GeoJSONCollection;

import java.util.List;

@Service
@AllArgsConstructor
public class AdServiceImpl implements AdService{

    @Autowired
    private AdRepository adRepository;

    @Override
    public List<Ad> findAll() {
        return adRepository.findAll();
    }

    @Override
    public Ad findById(int id) {
        return adRepository.findById(id).get();
    }

    @Override
    public Ad findByAuthor() {
        return null;
    }

    @Override
    public GeoJSONCollection getAllCoordinates() throws JsonProcessingException {
        List<Ad> allAds = findAll();
        GeoJSONCollection coordinates = new GeoJSONCollection();
        for (Ad ad : allAds) {
            coordinates.addFeature(ad);
        }
        return coordinates;
    }
}
