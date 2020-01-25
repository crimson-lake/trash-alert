package pl.zielinska.trashAlert.service;

import pl.zielinska.trashAlert.entity.Ad;
import pl.zielinska.trashAlert.entity.geoJSON.GeoJSON;

import java.util.List;

public interface AdService {

    List<Ad> findAll();
    Ad findByAuthor();
    List<GeoJSON> getAllCoordinates();
}
