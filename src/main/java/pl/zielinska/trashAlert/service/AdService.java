package pl.zielinska.trashAlert.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.zielinska.trashAlert.entity.Ad;
import pl.zielinska.trashAlert.entity.geoJSON.GeoJSONCollection;

import java.util.List;

public interface AdService {

    List<Ad> findAll();
    Ad findByAuthor();
    Ad findById(int id);
    GeoJSONCollection getAllCoordinates() throws JsonProcessingException;
}
