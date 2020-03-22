package pl.zielinska.trashAlert.service;

import pl.zielinska.trashAlert.domain.geoJSON.GeoJSONCollection;

public interface GeoService {
    GeoJSONCollection getCoordinates();
}
