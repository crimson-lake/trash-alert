package pl.zielinska.trashAlert.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.zielinska.trashAlert.domain.Ad;
import pl.zielinska.trashAlert.domain.geoJSON.GeoJSONCollection;

import java.util.List;

public interface AdService {

    List<Ad> findAll();
    Ad findByAuthor();
    Ad findById(int id);
    void save(Ad theAd);
    GeoJSONCollection getAllCoordinates() throws JsonProcessingException;
}
