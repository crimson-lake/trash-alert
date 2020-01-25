package pl.zielinska.trashAlert.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.zielinska.trashAlert.entity.Ad;
import pl.zielinska.trashAlert.entity.geoJSON.GeoJSON;

import java.util.List;

public interface AdService {

    List<Ad> findAll();
    Ad findByAuthor();
    Ad findById(int id);
    List<GeoJSON> getAllCoordinates() throws JsonProcessingException;
}
